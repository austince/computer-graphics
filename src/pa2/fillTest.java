package pa2;
//
//  fillTest.java
//
//  Created by Srinivas Sridharan on 2/10/17.
//  Copyright 2017 Stevens Institute of Technology. All rights reserved.
//
//  This file should not be modified by students.
//

import java.awt.*;
import java.awt.event.*;

public class fillTest implements KeyListener {
    
    private simpleCanvas T;
    private Rasterizer R;

    public fillTest() {
        
        this.T = new simpleCanvas( 901, 601 );
        this.R = new Rasterizer( 601 );

        this.T.setColor( 0.0f, 0.0f, 0.0f );
        this.T.clear();
        this.T.setColor( 1.0f, 0.0f, 0.0f );

        int x[] = new int[10];
        int y[] = new int[10];


        // ET Build Example
//        x[0] = 2;  y[0] = 3;
//        x[1] = 7;  y[1] = 1;
//        x[2] = 13;  y[2] = 5;
//        x[3] = 13;  y[3] = 11;
//        x[4] = 7;  y[4] = 7;
//        x[5] = 2;  y[5] = 9;
//        this.R.drawPolygon( 6, x, y, this.T );

        // ########### BONUS ###########
        x[0] = 100;  y[0] = 400;
        x[1] = 160;  y[1] = 400;
        x[2] = 140;  y[2] = 420;
        x[3] = 160;  y[3] = 440;
        x[4] = 100;  y[4] = 440;
        x[5] = 120;  y[5] = 420;
        this.T.setColor( 0.702f, 0.616f, 0.859f );
        this.R.drawPolygon( 6, x, y, this.T );
        // ########### TEAPOT START ###########
        // BASE
        this.T.setColor( 1.0f, 0.0f, 0.0f );
        x[0] = 760;  y[0] = 40;
        x[1] = 600;  y[1] = 40;
        x[2] = 620;  y[2] = 60;
        x[3] = 740;  y[3] = 60;
        this.R.drawPolygon( 4, x, y, this.T );
    
        // RIGHT BOTTOM TRIANGLE
        x[0] = 800;  y[0] = 120;
        x[1] = 740;  y[1] = 60;
        x[2] = 620;  y[2] = 60;
        this.T.setColor( 0.90f, 0.0f, 0.0f );
        this.R.drawPolygon( 3, x, y, this.T );
    
        // SPOUT
        x[0] = 620;  y[0] = 60;
        x[1] = 560;  y[1] = 100;
        x[2] = 500;  y[2] = 180;
        this.T.setColor( 0.80f, 0.0f, 0.0f );
        this.R.drawPolygon( 3, x, y, this.T );
    
        x[0] = 620;  y[0] = 60;
        x[1] = 500;  y[1] = 180;
        x[2] = 460;  y[2] = 200;
        x[3] = 520;  y[3] = 200;
        x[4] = 580;  y[4] = 160;
        this.T.setColor( 0.7f, 0.0f, 0.0f );
        this.R.drawPolygon( 5, x, y, this.T );
    
        x[0] = 620;  y[0] = 60;
        x[1] = 580;  y[1] = 160;
        x[2] = 620;  y[2] = 240;
        x[3] = 740;  y[3] = 240;
        x[4] = 800;  y[4] = 120;
        this.T.setColor( 0.6f, 0.0f, 0.0f );
        this.R.drawPolygon( 5, x, y, this.T );
    
        x[0] = 800;  y[0] = 120;
        x[1] = 840;  y[1] = 160;
        x[2] = 855;  y[2] = 200;
        x[3] = 720;  y[3] = 220;
        x[4] = 720;  y[4] = 200;
        x[5] = 830;  y[5] = 190;
        x[6] = 825;  y[6] = 165;
        x[7] = 780;  y[7] = 120;
        this.T.setColor( 0.5f, 0.0f, 0.0f );
        this.R.drawPolygon( 8, x, y, this.T );
    
        x[0] = 690;  y[0] = 240;
        x[1] = 710;  y[1] = 260;
        x[2] = 650;  y[2] = 260;
        x[3] = 670;  y[3] = 240;
        this.T.setColor( 0.4f, 0.0f, 0.0f );
        this.R.drawPolygon( 4, x, y, this.T );
    
        // ######## TRIANGLE #######
        x[0] = 460;  y[0] = 220;
        x[1] = 490;  y[1] = 280;
        x[2] = 420;  y[2] = 280;
        this.T.setColor( 0.0f, 1.0f, 0.0f );
        this.R.drawPolygon( 3, x, y, this.T );
    
        // ########## QUAD ##########
        x[0] = 380;  y[0] = 280;
        x[1] = 320;  y[1] = 320;
        x[2] = 360;  y[2] = 380;
        x[3] = 420;  y[3] = 340;
        this.T.setColor( 0.0f, 0.8f, 0.8f );
        this.R.drawPolygon( 4, x, y, this.T );
    
        // ############ STAR #############
        // RIGHT SIDE
        x[0] = 230;  y[0] = 389;
        x[1] = 260;  y[1] = 369;
        x[2] = 254;  y[2] = 402;
        x[3] = 278;  y[3] = 425;
        x[4] = 245;  y[4] = 430;
        x[5] = 230;  y[5] = 460;
        x[6] = 230;  y[6] = 410;
        this.T.setColor( 0.8f, 0.8f, 0.0f );
        this.R.drawPolygon( 7, x, y, this.T );
    
        // LEFT SIDE
        x[0] = 230;  y[0] = 460;
        x[1] = 216;  y[1] = 430;
        x[2] = 183;  y[2] = 425;
        x[3] = 207;  y[3] = 402;
        x[4] = 201;  y[4] = 369;
        x[5] = 230;  y[5] = 389;
        x[6] = 230;  y[6] = 410;
        this.T.setColor( 0.7f, 0.7f, 0.0f );
        this.R.drawPolygon( 7, x, y, this.T );
    
        // ########## BORDERS ###############
        // SQUARE BOTTOM LEFT
        x[0] = 0;    y[0] = 0;
        x[1] = 0;    y[1] = 20;
        x[2] = 20;   y[2] = 20;
        x[3] = 20;   y[3] = 0;
        this.T.setColor( 0.0f, 0.0f, 1.0f );
        this.R.drawPolygon( 4, x, y, this.T );
    
        x[0] = 0;    y[0] = 10;
        x[1] = 10;   y[1] = 10;
        x[2] = 10;   y[2] = 580;
        x[3] = 0;    y[3] = 580;
        this.T.setColor( 0.0f, 0.1f, 0.9f );
        this.R.drawPolygon( 4, x, y, this.T );
    
        x[0] = 0;    y[0] = 580;
        x[1] = 0;    y[1] = 600;
        x[2] = 20;   y[2] = 600;
        x[3] = 20;   y[3] = 580;
        this.T.setColor( 0.0f, 0.2f, 0.8f );
        this.R.drawPolygon( 4, x, y, this.T );
    
        //  TRIANGLE TOP:TOP
        x[0] = 10;   y[0] = 590;
        x[1] = 10;   y[1] = 600;
        x[2] = 880;  y[2] = 600;
        this.T.setColor( 0.0f, 0.3f, 0.7f );
        this.R.drawPolygon( 3, x, y, this.T );
    
        //  TRIANGLE TOP:BOTTOM
        x[0] = 10;   y[0] = 590;
        x[1] = 880;  y[1] = 590;
        x[2] = 880;  y[2] = 600;
        this.T.setColor( 0.0f, 0.4f, 0.6f );
        this.R.drawPolygon( 3, x, y, this.T );
    
        // SQUARE TOP RIGHT
        x[0] = 900;  y[0] = 600;
        x[1] = 900;  y[1] = 580;
        x[2] = 880;  y[2] = 580;
        x[3] = 880;  y[3] = 600;
        this.T.setColor( 0.1f, 0.4f, 0.5f );
        this.R.drawPolygon( 4, x, y, this.T );
    
        //  TRIANGLE RIGHT:RIGHT
        x[0] = 890;  y[0] = 580;
        x[1] = 900;  y[1] = 580;
        x[2] = 890;  y[2] = 20;
        this.T.setColor( 0.2f, 0.4f, 0.4f );
        this.R.drawPolygon( 3, x, y, this.T );
    
        //  TRIANGLE RIGHT:LEFT
        x[0] = 900;  y[0] = 580;
        x[1] = 900;  y[1] = 20;
        x[2] = 890;  y[2] = 20;
        this.T.setColor( 0.3f, 0.4f, 0.3f );
        this.R.drawPolygon( 3, x, y, this.T );
    
        // SQUARE BOTTOM RIGHT
        x[0] = 900;  y[0] = 0;
        x[1] = 900;  y[1] = 20;
        x[2] = 880;  y[2] = 20;
        x[3] = 880;  y[3] = 0;
        this.T.setColor( 0.4f, 0.4f, 0.2f );
        this.R.drawPolygon( 4, x, y, this.T );
    
        // QUAD BOTTOM
        x[0] = 20;   y[0] = 0;
        x[1] = 20;   y[1] = 10;
        x[2] = 880;  y[2] = 10;
        x[3] = 880;  y[3] = 0;
        this.T.setColor( 0.4f, 0.5f, 0.1f );
        this.R.drawPolygon( 4, x, y, this.T );
    }

    // Because we are a KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
        // What key did we type?
        char key = e.getKeyChar();

        if( (key == 'Q') || (key == 'q') ) System.exit(0); // quit

    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    static public void main(String[] args) {
        fillTest F = new fillTest();
	    F.T.addKeyListener( F );

        Frame f = new Frame( "Poly Fill Test" );
        f.add("Center", F.T);
        f.pack();
        f.setResizable (false);
        f.setVisible(true);
        
        f.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }

}
