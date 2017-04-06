package pa4;//
//  helloOpenGL.java
//
//  Main class for tessellation assignment.
//
//  Students should not be modifying this file.
//

import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;


public class helloOpenGL implements GLEventListener, KeyListener, MouseListener
{
    ///
    // shader info
    ///
    private int shaderProgID = 0;
    private boolean bufferInit = false;


    ///
    // buffer info
    ///
    private int vbuffer1;
    private int vbuffer2;
    private int ebuffer;

    ///
    // geometry data
    ///
    private float datapoints[] = {
         0.25f, -0.75f, 0.0f, 1.0f,
         0.50f, -0.75f, 0.0f, 1.0f,
         0.25f,  0.15f, 0.0f, 1.0f,

         0.50f, -0.75f, 0.0f, 1.0f,
         0.50f,  0.15f, 0.0f, 1.0f,
         0.25f,  0.15f, 0.0f, 1.0f,

         0.25f,  0.25f, 0.0f, 1.0f,
         0.50f,  0.25f, 0.0f, 1.0f,
         0.25f,  0.50f, 0.0f, 1.0f,

         0.50f,  0.25f, 0.0f, 1.0f,
         0.50f,  0.50f, 0.0f, 1.0f,
         0.25f,  0.50f, 0.0f, 1.0f,

        -0.25f, -0.75f, 0.0f, 1.0f,
         0.00f, -0.75f, 0.0f, 1.0f,
        -0.25f,  0.75f, 0.0f, 1.0f,

         0.00f, -0.75f, 0.0f, 1.0f,
         0.00f,  0.75f, 0.0f, 1.0f,
        -0.25f,  0.75f, 0.0f, 1.0f,

        -0.75f, -0.75f, 0.0f, 1.0f,
        -0.50f, -0.75f, 0.0f, 1.0f,
        -0.75f,  0.75f, 0.0f, 1.0f,

        -0.50f, -0.75f, 0.0f, 1.0f,
        -0.50f,  0.75f, 0.0f, 1.0f,
        -0.75f,  0.75f, 0.0f, 1.0f,

        -0.50f, -0.12f, 0.0f, 1.0f,
        -0.25f, -0.12f, 0.0f, 1.0f,
        -0.50f,  0.12f, 0.0f, 1.0f,

        -0.25f, -0.12f, 0.0f, 1.0f,
        -0.25f,  0.12f, 0.0f, 1.0f,
        -0.50f,  0.12f, 0.0f, 1.0f
    };

    private short elements[] = {
         0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
	15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29
    };

    private float colors1[] = {
        0.00f, 1.00f, 0.00f, 1.0f,
	0.00f, 1.00f, 0.00f, 1.0f,
	0.00f, 0.28f, 0.72f, 1.0f,
        0.00f, 1.00f, 0.00f, 1.0f,
        0.00f, 0.28f, 0.72f, 1.0f,
        0.00f, 0.28f, 0.72f, 1.0f,
        0.00f, 0.20f, 0.80f, 1.0f,
	0.00f, 0.20f, 0.80f, 1.0f,
	0.00f, 0.00f, 1.00f, 1.0f,
        0.00f, 0.20f, 0.80f, 1.0f,
	0.00f, 0.00f, 1.00f, 1.0f,
	0.00f, 0.00f, 1.00f, 1.0f,
        1.00f, 0.00f, 0.00f, 1.0f,
	1.00f, 0.00f, 0.00f, 1.0f,
	1.00f, 0.00f, 0.00f, 1.0f,
        1.00f, 0.00f, 0.00f, 1.0f,
	1.00f, 0.00f, 0.00f, 1.0f,
	1.00f, 0.00f, 0.00f, 1.0f,
        1.00f, 1.00f, 0.00f, 1.0f,
	1.00f, 1.00f, 0.00f, 1.0f,
	1.00f, 1.00f, 0.00f, 1.0f,
        1.00f, 1.00f, 0.00f, 1.0f,
	1.00f, 1.00f, 0.00f, 1.0f,
	1.00f, 1.00f, 0.00f, 1.0f,
        1.00f, 1.00f, 0.00f, 1.0f,
	1.00f, 0.00f, 0.00f, 1.0f,
	1.00f, 1.00f, 0.00f, 1.0f,
        1.00f, 0.00f, 0.00f, 1.0f,
	1.00f, 0.00f, 0.00f, 1.0f,
	1.00f, 1.00f, 0.00f, 1.0f
    };

    private float colors2[] = {
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
        1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f,
	1.00f, 1.00f, 1.00f, 1.0f
    };

    int nverts = 30;

    int dataSize;
    int colorSize1;
    int colorSize2;
    int elemSize;

    int counter;

    ///
    // my canvas
    ///
    GLCanvas myCanvas;
    private static Frame frame;

    ///
    // constructor
    ///
    public helloOpenGL( GLCanvas G )
    {
        myCanvas = G;

	counter = 0;

        dataSize   = nverts * 4 * 4;
	elemSize   = nverts * 2;
	colorSize1 = dataSize;
	colorSize2 = dataSize;

        G.addGLEventListener( this );
        G.addKeyListener( this );
	G.addMouseListener( this );
    }

    private void errorCheck( GL2 gl2 )
    {
        int code = gl2.glGetError();
        if( code == GL.GL_NO_ERROR )
            System.err.println( "All is well" );
        else
            System.err.println( "Problem - error code : " + code );

    }


    ///
    // Called by the drawable to initiate OpenGL rendering by the client.
    ///
    public void display( GLAutoDrawable drawable )
    {
        // get GL
        GL2 gl2 = (drawable.getGL()).getGL2();

        // clear your frame buffers
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );

        // bind the vertex and elements buffers
	if( (counter & 1) == 0 ) {
	    gl2.glBindBuffer( GL.GL_ARRAY_BUFFER, vbuffer1 );
	} else {
	    gl2.glBindBuffer( GL.GL_ARRAY_BUFFER, vbuffer2 );
	}
        gl2.glBindBuffer( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer );

        // set up your attribute variables
        gl2.glUseProgram( shaderProgID );
        int  vPosition = gl2.glGetAttribLocation( shaderProgID, "vPosition" );
        gl2.glEnableVertexAttribArray( vPosition );
        gl2.glVertexAttribPointer( vPosition, 4, GL.GL_FLOAT, false, 0, 0l );

        int  vColor = gl2.glGetAttribLocation( shaderProgID, "vColor" );
        gl2.glEnableVertexAttribArray( vColor );
        gl2.glVertexAttribPointer( vColor, 4, GL.GL_FLOAT, false, 0, dataSize );

        // draw your shapes
        gl2.glDrawElements( GL.GL_TRIANGLES, nverts, GL.GL_UNSIGNED_SHORT, 0l );

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
        GL2 gl2 = drawable.getGL().getGL2();

        // Load shaders
        shaderSetup myShaders = new shaderSetup();

        shaderProgID = myShaders.readAndCompile( gl2, "shader.vert",
	    "shader.frag");
        if( shaderProgID == 0 ) {
            System.err.println(
	        myShaders.errorString(myShaders.shaderErrorCode) );
            frame.dispose();
            System.exit( 1 );
        }

        // Other GL initialization
        gl2.glEnable( GL.GL_DEPTH_TEST );
        gl2.glEnable( GL.GL_CULL_FACE );
        gl2.glCullFace(  GL.GL_BACK );
        gl2.glFrontFace( GL.GL_CCW );
        gl2.glClearColor( 0.0f, 0.0f, 0.0f, 1.0f );
        gl2.glDepthFunc( GL.GL_LEQUAL );
        gl2.glClearDepth( 1.0f );

	// Create our buffers
	createShapes( gl2 );

    }

    ///
    // create a buffer
    ///
    int makeBuffer( GL2 gl2, int target, Buffer data, int size )
    {
        int buffer[] = new int[1];

	gl2.glGenBuffers( 1, buffer, 0 );
	gl2.glBindBuffer( target, buffer[0] );
	gl2.glBufferData( target, size, data, GL.GL_STATIC_DRAW );

	return( buffer[0] );
    }

    ///
    // creates a new shape
    ///
    public void createShapes( GL2 gl2 )
    {
        // get your vertices and elements
        Buffer pts   = FloatBuffer.wrap( datapoints );
        Buffer elems = ShortBuffer.wrap( elements );
        Buffer col1  = FloatBuffer.wrap( colors1 );
        Buffer col2  = FloatBuffer.wrap( colors2 );

        // set up the vertex buffers

        vbuffer1 = makeBuffer( gl2, GL.GL_ARRAY_BUFFER, null,
	    dataSize + colorSize1 );
        gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, 0, dataSize, pts );
        gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, dataSize, colorSize1, col1 );

        vbuffer2 = makeBuffer( gl2, GL.GL_ARRAY_BUFFER, null,
	    dataSize + colorSize2 );
        gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, 0, dataSize, pts );
        gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, dataSize, colorSize2, col2 );

        // set up element buffer.

	ebuffer = makeBuffer( gl2, GL.GL_ELEMENT_ARRAY_BUFFER, elems,
	    elemSize );

        // we're all done
        bufferInit = true;

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
    // Because I am a Key Listener...we'll only respond to key presses
    ///
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    ///
    // Invoked when a key has been pressed.
    ///
    public void keyPressed(KeyEvent e)
    {
        // Get the key that was pressed
        char key = e.getKeyChar();

        // Respond appropriately
        switch( key ) {
	    case '1':
	        counter = 0;
		break;

	    case '2':
	        counter = 1;
		break;

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
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        // create your main
        helloOpenGL myMain = new helloOpenGL(canvas);

        frame = new Frame("Hello, OpenGL!");
        frame.setSize( 512, 512 );
        frame.add( canvas );
        frame.setVisible( true );

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

    @Override
    public void mouseClicked( MouseEvent arg0 ) {
        // want to cycle through the screens on mouse click
        counter = counter + 1;
        
	// do a redraw
        myCanvas.display();
    }

    @Override
    public void mouseEntered( MouseEvent arg0 ) {
        // not used
    }

    @Override
    public void mouseExited( MouseEvent arg0 ) {
        // not used
    }

    @Override
    public void mousePressed( MouseEvent arg0 ) {
        // not used
    }

    @Override
    public void mouseReleased( MouseEvent arg0 ) {
        // not used
    }

}
