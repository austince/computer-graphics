//
//  LineTest.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2014 Rochester Institute of Technology. All rights reserved.
//
//  Modified by Vasudev Prasad Bethamcherla on 08/19/2014
//
//  Contributor:  Austin Cawley-Edwards
//
package pa1;

import java.awt.*;
import java.awt.event.*;

public class LineTest implements KeyListener {

    private static int drawHeight = 600;
    private static int drawWidth = 600;

    public SimpleCanvas canvas;
    public Rasterizer rasterizer;

    /**
     *
     * @param w - widith
     * @param h - height
     */
    private LineTest(int w, int h) {
        this.canvas = new SimpleCanvas(w, h);
        this.rasterizer = new Rasterizer(w);

        this.canvas.setColor (0.0f, 0.0f, 0.0f);
        this.canvas.clear();
        this.canvas.setColor (1.0f, 1.0f, 1.0f);

// Idea for lettering style from:
// http://patorjk.com/software/taag/#p=display&f=Star%20Wars&t=Type%20Something
//          _______   ______   
//         /  _____| /  __  \
//        |  |  __  |  |  |  | 
//        |  | |_ | |  |  |  | 
//        |  |__| | |  `--'  | 
//         \______|  \______/

        // ######## The letter 'G' in green ########
        this.canvas.setColor( 0.0f, 1.0f, 0.0f );
        this.rasterizer.drawLine( 80, 340, 220, 340, this.canvas);   // Horizontal left to right
        this.rasterizer.drawLine( 40, 380, 80, 340, this.canvas);    // 315 degree slope
        this.rasterizer.drawLine( 220, 340, 260, 380, this.canvas);  // 45 degree slope
        this.rasterizer.drawLine( 260, 380, 260, 440, this.canvas);  // Vertical bottom to top
        this.rasterizer.drawLine( 260, 440, 180, 440, this.canvas);  // Horizontal right to left
        this.rasterizer.drawLine( 180, 440, 180, 400, this.canvas);
        this.rasterizer.drawLine( 180, 400, 220, 400, this.canvas);
        this.rasterizer.drawLine( 220, 400, 200, 380, this.canvas);
        this.rasterizer.drawLine( 200, 380, 100, 380, this.canvas);
        this.rasterizer.drawLine( 100, 380, 80, 400, this.canvas);
        this.rasterizer.drawLine( 80, 400, 80, 500, this.canvas);
        this.rasterizer.drawLine( 80, 500, 100, 520, this.canvas);
        this.rasterizer.drawLine( 100, 520, 200, 520, this.canvas);
        this.rasterizer.drawLine( 200, 520, 220, 500, this.canvas);
        this.rasterizer.drawLine( 220, 500, 220, 480, this.canvas);
        this.rasterizer.drawLine( 220, 480, 260, 480, this.canvas);
        this.rasterizer.drawLine( 260, 480, 260, 520, this.canvas);
        this.rasterizer.drawLine( 260, 520, 220, 560, this.canvas);  // 135 degree slope
        this.rasterizer.drawLine( 220, 560, 80, 560, this.canvas);
        this.rasterizer.drawLine( 80, 560, 40, 520, this.canvas);    // 225 degree slope
        this.rasterizer.drawLine( 40, 520, 40, 380, this.canvas);    // Vertical top to bottom

        System.out.println("Done the G");

        // ######## The letter 'O' in orange ########
        this.canvas.setColor( 1.0f, 0.647f, 0.0f );
        // Right side
        this.rasterizer.drawLine( 450, 320, 520, 340, this.canvas);  // 16.6 degree slope
        this.rasterizer.drawLine( 520, 340, 540, 360, this.canvas);  // 45 degree slope
        this.rasterizer.drawLine( 540, 360, 560, 450, this.canvas);  // 77.47 degree slope

        // Top right
        this.rasterizer.drawLine( 560, 450, 540, 540, this.canvas);  // 102.83 degree slope
        this.rasterizer.drawLine( 540, 540, 520, 560, this.canvas);  // 135 degree slope
        this.rasterizer.drawLine( 520, 560, 450, 580, this.canvas);  // 163.3 degree slope
//        Left side
        // Top left
        this.rasterizer.drawLine( 450, 580, 380, 560, this.canvas);  // 196.71 degree slope
        this.rasterizer.drawLine( 380, 560, 360, 540, this.canvas);  // 225 degree slope


        this.rasterizer.drawLine( 360, 540, 340, 450, this.canvas);
        this.rasterizer.drawLine( 340, 450, 360, 360, this.canvas);
        this.rasterizer.drawLine( 360, 360, 380, 340, this.canvas);
        this.rasterizer.drawLine( 380, 340, 450, 320, this.canvas);
        // Inner
        this.rasterizer.drawLine( 420, 380, 480, 380, this.canvas);
        this.rasterizer.drawLine( 480, 380, 520, 420, this.canvas);
        this.rasterizer.drawLine( 520, 420, 520, 480, this.canvas);
        this.rasterizer.drawLine( 520, 480, 480, 520, this.canvas);
        this.rasterizer.drawLine( 480, 520, 420, 520, this.canvas);
        this.rasterizer.drawLine( 420, 520, 380, 480, this.canvas);
        this.rasterizer.drawLine( 380, 480, 380, 420, this.canvas);
        this.rasterizer.drawLine( 380, 420, 420, 380, this.canvas);

        System.out.println("Done the O");

        // ##### Use light blue to write your initials #####

        this.canvas.setColor( 0.678f, 0.847f, 0.902f );

        // Draws ACE
        // from x=40 -> x=560
        // from y=20 -> y=300
        // A
        this.rasterizer.drawLine(40,20, 40,300, this.canvas);
        this.rasterizer.drawLine(200,300, 40,300, this.canvas);
        this.rasterizer.drawLine(200,20, 200,300, this.canvas);
        this.rasterizer.drawLine(200,20, 160,20, this.canvas);
        this.rasterizer.drawLine(160,120, 160,20, this.canvas);
        this.rasterizer.drawLine(160,120, 80,120, this.canvas);
        this.rasterizer.drawLine(80,20, 80,120, this.canvas);
        this.rasterizer.drawLine(80,20, 40,20, this.canvas);
        // A inner
//        this.rasterizer.drawLine(80,230, 80,160, this.canvas);
//        this.rasterizer.drawLine(80,230, 160,230, this.canvas);
//        this.rasterizer.drawLine(160,160, 160,230, this.canvas);
//        this.rasterizer.drawLine(160,160, 80,160, this.canvas);

        this.rasterizer.drawCircle(120, 195, 40, this.canvas);

        // C
        this.rasterizer.drawLine(220,300, 380,300, this.canvas);
        this.rasterizer.drawLine(380,230, 380,300, this.canvas);
        this.rasterizer.drawLine(380,230, 260,230, this.canvas);
        // Vertical
        this.rasterizer.drawLine(260,90, 260,230, this.canvas);
        this.rasterizer.drawLine(261,90, 261,230, this.canvas);

        this.rasterizer.drawLine(260,90, 380,90, this.canvas);
        this.rasterizer.drawLine(380,20, 380,90, this.canvas);
        this.rasterizer.drawLine(380,20, 220,20, this.canvas);
        this.rasterizer.drawLine(220,300, 220,20, this.canvas);

        // E
        this.rasterizer.drawLine(400,300, 560,300, this.canvas);
        this.rasterizer.drawLine(560,230, 560,300, this.canvas);
        this.rasterizer.drawLine(560,230, 440,230, this.canvas);

        this.rasterizer.drawLine(440,195, 440,230, this.canvas);
        this.rasterizer.drawLine(440,195, 560,195, this.canvas);
        this.rasterizer.drawLine(560,125, 560,195, this.canvas);
        this.rasterizer.drawLine(560,125, 440,125, this.canvas);
        this.rasterizer.drawLine(440,90, 440,125, this.canvas);

        this.rasterizer.drawLine(440,90, 560,90, this.canvas);
        this.rasterizer.drawLine(560,20, 560,90, this.canvas);
        this.rasterizer.drawLine(560,20, 400,20, this.canvas);
        this.rasterizer.drawLine(400,300, 400,20, this.canvas);

        this.canvas.addKeyListener(this);
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
        LineTest test = new LineTest(LineTest.drawWidth, LineTest.drawHeight);

        Frame frame = new Frame( "Line Test" );
        frame.add("Center", test.canvas);
        frame.pack();
        frame.setResizable (false);
        frame.setVisible(true);

        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        } );
    }
}
