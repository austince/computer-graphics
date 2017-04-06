//
//  tessMain.java
//
//  Main class for tessellation assignment.
//
//  Students should not be modifying this file.
//
//
package pa5;
import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.Animator;

public class tessMain implements GLEventListener, KeyListener
{
    ///
    // static values
    ///
    public final static int CUBE = 0;
    public final static int CYLINDER = 1;
    public final static int CONE = 2;
    public final static int SPHERE = 3;

    ///
    // values for levels of subdivisions
    ///
    private int division1 = 1;
    private int division2 = 1;
    private int currentShape = tessMain.CUBE;

    ///
    // buffer information
    ///
    private boolean bufferInit = false;
    private int vbuffer;
    private int ebuffer;

    ///
    // shader arguments
    ///
    private int theta;      // theta uniform location
    private int vPosition;  // vertex attribute location

    ///
    // rotation angles
    ///
    public float angles[];
    public float anglesReset[];
    private float angleInc = 5.0f;

    ///
    // animation control
    ///
    private boolean animating = false;
    private int level = 0;
    Animator anime;

    ///
    // shader info
    ///
    private shaderSetup myShaders;
    private int shaderProgID = 0;
    private boolean updateNeeded = true;

    ///
    // canvas and shape info
    ///
    GLCanvas myCanvas;
    cgShape myShape;
    private static Frame frame;

    ///
    // constructor
    ///
    public tessMain(GLCanvas G)
    {
        angles = new float[3];
        angles[0] = 30.0f;
        angles[1] = 30.0f;
        angles[2] = 0.0f;

        anglesReset = new float[3];
        anglesReset[0] = 30.0f;
        anglesReset[1] = 30.0f;
        anglesReset[2] = 0.0f;

        myShaders = new shaderSetup();
        myShape = new cgShape();
        myCanvas = G;

        G.addGLEventListener (this);
        G.addKeyListener (this);
    }

    private void errorCheck (GL3 gl3)
    {
        int code = gl3.glGetError();
        if (code == GL.GL_NO_ERROR)
            System.err.println ("All is well");
        else
            System.err.println ("Problem - error code : " + code);

    }

    ///
    // Called by the drawable to initiate OpenGL rendering by the client.
    ///
    public void display(GLAutoDrawable drawable)
    {
        // get GL
        GL3 gl3 = (drawable.getGL()).getGL3();

        if( animating ) {
            animate();
        }

        // This should all probably be in createNewShape...  However,
        // since we can only get access to the GL3 during display, we'll
        // have to include it here.
        if (updateNeeded) {

            // get your vertices and elements
            Buffer points = myShape.getVertices();
            Buffer elements = myShape.getElements();

            // set up the vertex buffer
            int bf[] = new int[1];
            if (bufferInit) {
                bf[0] = vbuffer;
                gl3.glDeleteBuffers(1, bf, 0);
            }
            gl3.glGenBuffers (1, bf, 0);
            vbuffer = bf[0];
            long vertBsize = myShape.getNVerts() * 4l * 4l;
            gl3.glBindBuffer ( GL.GL_ARRAY_BUFFER, vbuffer);
            gl3.glBufferData ( GL.GL_ARRAY_BUFFER, vertBsize , points, GL.GL_STATIC_DRAW);
            bufferInit = true;

            // set up element buffer.
            if (bufferInit) {
                bf[0] = ebuffer;
                gl3.glDeleteBuffers(1, bf, 0);
            }
            gl3.glGenBuffers (1, bf, 0);
            ebuffer = bf[0];
            long eBuffSize = myShape.getNVerts() * 2l;
            gl3.glBindBuffer ( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer);
            gl3.glBufferData ( GL.GL_ELEMENT_ARRAY_BUFFER, eBuffSize,elements,
                    GL.GL_STATIC_DRAW);

            // we're all done
            updateNeeded = false;
        }

        // clear the frame buffer
        gl3.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );

        // bind our vertex buffer
        gl3.glBindBuffer ( GL.GL_ARRAY_BUFFER, vbuffer);

        // bind our element array buffer
        gl3.glBindBuffer ( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer);

        // ensure we have selected the correct shader program
        gl3.glUseProgram (shaderProgID);

        // set up our attribute variables
        gl3.glEnableVertexAttribArray ( vPosition );
        gl3.glVertexAttribPointer (vPosition, 4, GL.GL_FLOAT, false,
                0, 0l);

        // pass in our rotations as a uniform variable
        gl3.glUniform3fv (theta, 1, angles, 0);

        // draw our shapes
        int nElems = myShape.getNVerts();
        gl3.glDrawElements ( GL.GL_TRIANGLES, nElems,  GL.GL_UNSIGNED_SHORT, 0l);

    }

    ///
    // Notifies the listener to perform the release of all OpenGL
    // resources per GLContext, such as memory buffers and GLSL
    // programs.
    ///
    public void dispose(GLAutoDrawable drawable)
    {

    }

    ///
    // Called by the drawable immediately after the OpenGL context is
    // initialized.
    ///
    public void init(GLAutoDrawable drawable)
    {
        // get the gl object
        GL3 gl3 = drawable.getGL().getGL3();

        // create the Animator now that we have the drawable
        anime = new Animator( drawable );

        // Load shaders
        shaderProgID = myShaders.readAndCompile (gl3, "shader.vert", "shader.frag");
        if (shaderProgID == 0) {
            System.err.println ("Error setting up shaders");
            System.exit (1);
        }

        // select this shader program
        gl3.glUseProgram( shaderProgID );

        // get location of shader global variables
        vPosition = gl3.glGetAttribLocation (shaderProgID, "vPosition");
        theta = gl3.glGetUniformLocation (shaderProgID, "theta");

        // Other GL initialization
        gl3.glEnable( GL.GL_DEPTH_TEST );
        gl3.glEnable( GL.GL_CULL_FACE );
        gl3.glCullFace( GL.GL_BACK );
        gl3.glPolygonMode( GL.GL_FRONT_AND_BACK, GL3.GL_LINE );
        gl3.glFrontFace( GL.GL_CCW );
        gl3.glClearColor( 0.0f, 0.0f, 0.0f, 1.0f );
        gl3.glDepthFunc( GL.GL_LEQUAL );
        gl3.glClearDepth( 1.0f );

        // initially create a new Shape
        createNewShape();

    }

    ///
    // Called by the drawable during the first repaint after the component
    // has been resized.
    ///
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height)
    {

    }

    ///
    // creates a new shape
    ///
    public void createNewShape()
    {
        // clear the old shape
        myShape.clear();

        // create the new shape...should be a switch here
        switch (currentShape)
        {
            case CUBE: myShape.makeCube (division1);
                break;

            case CYLINDER: myShape.makeCylinder (0.5f, division1, division2);
                break;

            case CONE: myShape.makeCone (0.5f, division1, division2);
                break;

            case SPHERE: myShape.makeSphere (0.5f, division1, division2);
                break;
        }

        updateNeeded = true;
    }

    ///
    // Because I am a Key Listener...we'll only respond to key presses
    ///
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    ///
    // Rotates the shapes along x,y,z.
    // 
    // Causes gimbal lock which also happened on Apollo 11
    // http://en.wikipedia.org/wiki/Gimbal_lock#Gimbal_lock_on_Apollo_11
    // Solution? Use Quaternions (Taught in Comp. Animation: Algorithms)
    // 
    // TIDBIT:
    // Quaternion plaque on Brougham (Broom) Bridge, Dublin, which says:
    // 
    //  "Here as he walked by
    //  on the 16th of October 1843
    //  
    //  Sir William Rowan Hamilton 
    //  
    //  in a flash of genius discovered
    //  the fundamental formula for
    //  quaternion multiplication
    //  i^2 = j^2 = k^2 = ijk = -1
    //  & cut it on a stone of this bridge"
    ///
    public void animate() {

        if( level >= 450 ) {
            level = 0;
            animating = false;
        }

        if( !animating ) {
            anime.stop();
            return;
        }

        if( level < 150 ) {
            angles[0] -= angleInc / 3;
        } else if( level < 300 ) {
            angles[1] -= angleInc / 3;
        } else {
            angles[2] -= angleInc / 3;
        }

        ++level;

        // myCanvas.display();
        updateNeeded = true;
    }

    ///
    // Invoked when a key has been pressed.
    ///
    public void keyPressed(KeyEvent e)
    {
        // Get the key that was pressed
        char key = e.getKeyChar();

        // Respond appropriately
        switch( key ) {

            // automated animation
            case 'a': case 'A':
                animating = true;
                anime.start();
                break;

            // incremental rotation along the axes
            case 'x': angles[0] -= angleInc; break;
            case 'y': angles[1] -= angleInc; break;
            case 'z': angles[2] -= angleInc; break;
            case 'X': angles[0] += angleInc; break;
            case 'Y': angles[1] += angleInc; break;
            case 'Z': angles[2] += angleInc; break;

            // shape selection
            case '1': case 'c': currentShape = CUBE; createNewShape(); break;
            case '2': case 'C': currentShape = CYLINDER; createNewShape(); break;
            case '3': case 'n': currentShape = CONE; createNewShape(); break;
            case '4': case 's': currentShape = SPHERE; createNewShape(); break;

            // tessellation factors
            case '+': division1++; createNewShape(); break;
            case '=': division2++; createNewShape(); break;
            case '-': if( division1 > 1 ) {
                division1--;
                createNewShape();
            }
                break;
            case '_': if( division2 > 1 ) {
                division2--;
                if (currentShape != CUBE) createNewShape();
            }
                break;

            // reset rotation
            case 'r': case 'R':
                angles[0] = anglesReset[0];
                angles[1] = anglesReset[1];
                angles[2] = anglesReset[2];
                break;

            // termination
            case 033:  // Escape key
            case 'q': case 'Q':
                frame.dispose();
                System.exit( 0 );
                break;
        }

        // do a redraw
        myCanvas.display();
    }

    ///
    // main program
    ///
    public static void main(String [] args)
    {
        // GL setup
        GLProfile glp = GLProfile.get( GLProfile.GL3 );
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        // create your tessMain
        tessMain myMain = new tessMain(canvas);

        frame = new Frame("CG - Tessellation Assignment");
        frame.setSize(512, 512);
        frame.add(canvas);
        frame.setVisible(true);

        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
    }
}