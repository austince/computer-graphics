package pa3;//
//  clipTest.java
//
//  Created by Srinivas Sridharan on 2/17/2017.
//  Copyright 2017 Stevens Institute of Technology. All rights reserved.
//
//  This file should not be modified by students.
//

import java.awt.*;
import java.awt.event.*;

public class clipTest implements KeyListener {

    private extendedCanvas T;
    private clipper C;

    private void drawClipRegion( float llx, float lly, float urx, float ury )
    {
        float x[] = new float[4];
        float y[] = new float[4];

        x[0] = llx; y[0] = lly;
        x[1] = urx; y[1] = lly;
        x[2] = urx; y[2] = ury;
        x[3] = llx; y[3] = ury;

        T.printLoop( 4, x, y );
    }

    clipTest()
    {

        T = new extendedCanvas(300, 300);
        C = new clipper();

        T.setColor (0.0f, 0.0f, 0.0f);
        T.clear();

        float quad1x[] = new float[4];
        float quad1y[] = new float[4];
        quad1x[0] =  20; quad1x[1] =  20; quad1x[2] =  40; quad1x[3] =  40;
        quad1y[0] = 120; quad1y[1] = 140; quad1y[2] = 140; quad1y[3] = 120;

        float quad2x[] = new float[4];
        float quad2y[] = new float[4];
        quad2x[0] =  80; quad2x[1] =  80; quad2x[2] =  60; quad2x[3] =  60;
        quad2y[0] = 160; quad2y[1] = 200; quad2y[2] = 200; quad2y[3] = 160;

        float quad3x[] = new float[4];
        float quad3y[] = new float[4];
        quad3x[0] = 20; quad3x[1] = 50; quad3x[2] = 50; quad3x[3] = 20;
        quad3y[0] = 60; quad3y[1] = 60; quad3y[2] = 50; quad3y[3] = 50;

        float quad4x[] = new float[4];
        float quad4y[] = new float[4];
        quad4x[0] =  44; quad4x[1] =  60; quad4x[2] =  60; quad4x[3] =  44;
        quad4y[0] = 122; quad4y[1] = 122; quad4y[2] = 146; quad4y[3] = 146;

        float pent1x[] = new float[5];
        float pent1y[] = new float[5];
        pent1x[0] = 80; pent1x[1] = 90; pent1x[2] = 110; pent1x[3] = 100; pent1x[4] = 80;
        pent1y[0] = 20; pent1y[1] = 10; pent1y[2] = 20; pent1y[3] = 50; pent1y[4] = 40;

        float hept1x[] = new float[7];
        float hept1y[] = new float[7];
        hept1x[0] = 120; hept1x[1] = 140; hept1x[2] = 160; hept1x[3] = 160; hept1x[4] = 140; hept1x[5] = 120; hept1x[6] = 110;
        hept1y[0] = 70; hept1y[1] = 70; hept1y[2] = 80; hept1y[3] = 100; hept1y[4] = 110; hept1y[5] = 100; hept1y[6] = 90;

        // Is this not a risky way to do this? What if they need reallocation?
        // Why not just return a new array of Points for output?
        float wx[] = new float [50];
        float wy[] = new float [50];
        int wl;

        ///
	// Draw the clipping regions
	///

	T.setColor( 1.0f, 1.0f, 1.0f );
	drawClipRegion(  10, 110,  50, 150 );
        drawClipRegion(  30,  10,  70,  80 );
        drawClipRegion(  90,  34, 120,  60 );
        drawClipRegion(  90,  80, 130, 110 );

        ///
        // first polygon:  entirely within region
        ///

//// WORKS
        wl = 0;
        wl = C.clipPolygon( 4, quad1x, quad1y,  wx, wy, 10, 110, 50, 150 );
        T.setColor ( 1.0f, 0.0f, 0.0f );        // red
        T.printLoop( 4, quad1x, quad1y );
        T.printPoly( wl, wx, wy );

        ///
        // second polygon:  entirely outside region
        ///

//// WORKS
        wl = 0;
        T.setColor ( 0.0f, 1.0f, 0.0f );        // green
        T.printLoop( 4, quad2x, quad2y );
        wl = C.clipPolygon( 4, quad2x, quad2y, wx, wy, 10, 110, 50, 150 );
        // shouldn't draw anything!
        if( wl > 0 ) {
            T.printPoly( wl, wx, wy );
        }

        ///
        // third polygon:  halfway outside on left
        ///
//// WORKS
        wl = 0;
        wl = C.clipPolygon( 4, quad3x, quad3y, wx, wy, 30, 10, 70, 80 );
        T.setColor ( 0.0f, 0.0f, 1.0f );        // blue
        T.printLoop( 4, quad3x, quad3y );
        T.printPoly( wl, wx, wy );

        ///
        // fourth polygon:  part outside on right
        ///

//// WORKS
        wl = 0;
        wl = C.clipPolygon( 4, quad4x, quad4y, wx, wy, 10, 110, 50, 150 );
        T.setColor ( 1.0f, 0.0f, 1.0f );        // magenta
        T.printLoop( 4, quad4x, quad4y );
        T.printPoly( wl, wx, wy );

        ///
        // fifth polygon:  outside on left and bottom
        ///
//      WORKS
        wl = 0;
        wl = C.clipPolygon( 5, pent1x, pent1y, wx, wy, 90, 34, 120, 60 );
        T.setColor ( 1.0f, 0.5f, 1.0f );        // red-greenish-blue
        T.printLoop( 5, pent1x, pent1y );
        T.printPoly( wl, wx, wy );

        ///
        // sixth polygon:  outside on top, right, and bottom
        ///

        // WORKS
        wl = 0;
        wl = C.clipPolygon( 7, hept1x, hept1y, wx, wy, 90, 80, 130, 110 );
        T.setColor ( 0.7f, 0.7f, 0.7f );        // gray
        T.printLoop( 7, hept1x, hept1y );
        T.printPoly( wl, wx, wy );

    }

    // Because we are a KeyListener
    public void keyTyped(KeyEvent e)
    {
        // What key did we type?
        char key = e.getKeyChar();

        if( (key == 'Q') || (key == 'q') ) System.exit(0); // quit

    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    static public void main(String[] args)
    {
        clipTest C = new clipTest();
        C.T.addKeyListener( C );

        Frame f = new Frame( "Clipping Test" );
        f.add("Center", C.T);
        f.pack();
        f.setResizable (false);
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
