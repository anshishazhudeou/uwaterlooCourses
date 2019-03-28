/*
* CS 349 Java Code Examples
*
* ShapeDemo    Demo of Shape class: draw shapes using mouse.
*
*/
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

import javax.vecmath.*;

// create the window and run the demo
public class ShapeDemo extends JPanel {

    Shape shape;

    ShapeDemo() {

        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e) { 

                shape = new Shape();
                // change shape type
                shape.setIsClosed(true);
                // shape.setIsFilled(true);
                shape.setColour(Color.BLUE);

                // try setting scale to something other than 1 
                shape.setScale(1.0f);

                repaint();
            }
        });

        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e) {
                shape.addPoint(e.getX(), e.getY());
                repaint();      
            }
        }); 
    }

    public static void main(String[] args) {
        // create the window         
        ShapeDemo canvas = new ShapeDemo();
        JFrame f = new JFrame("ShapeDemo"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 300); // window size
        f.setContentPane(canvas); // add canvas to jframe
        f.setVisible(true); // show the window
    }
    // custom graphics drawing 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // cast to get 2D drawing methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  // antialiasing look nicer
                            RenderingHints.VALUE_ANTIALIAS_ON);

        if (shape != null)
            shape.draw(g2);
    }
}

