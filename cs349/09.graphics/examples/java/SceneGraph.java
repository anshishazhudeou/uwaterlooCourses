/*
* CS 349 Java Code Examples
*
* SceneGraph   Demonstrate simple scene graph that draws a house.
*
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.*;

public class SceneGraph extends JPanel {

    public static void main(String[] args) {
        // create the window
        JFrame f = new JFrame("SceneGraph"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400); // window size
        f.setContentPane(new SceneGraph()); // add canvas to jframe
        f.setVisible(true); // show the window

    }

    SceneGraph() {
        setOpaque(true);
        setBackground(Color.WHITE);

        // make sure panel is focusable for getting key events
        // (remember focus dispatch?)
        setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                switch(e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        rotateBy += 5;
                        break;
                    case KeyEvent.VK_LEFT:
                        rotateBy -= 5;
                        break;
                    case KeyEvent.VK_UP:
                        scaleBy += .1;
                        break;
                    case KeyEvent.VK_DOWN:
                        scaleBy -= .1;
                        break;

                }
                repaint();
            }
        });
    }

    // scaleBy and rotation
    double scaleBy = 2;
    double rotateBy = 0;

    // custom graphics drawing
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        // draw the house in centre of screen
        g2.translate(getWidth() / 2, getHeight()/ 2);
        g2.rotate(Math.toRadians(rotateBy));
        g2.scale(scaleBy, scaleBy);

        g2.setStroke(new BasicStroke(3));
        g2.drawPolygon(houseShape.xpoints, houseShape.ypoints, houseShape.npoints);

        // save transform for later
        AffineTransform save = g2.getTransform();

        // remember: these are all in "house coordinates"
        g2.translate(-25, 0); // window centred 25 px
        g2.scale(0.4, 0.4); // window is 40% house width
        drawWindow(g2);

        // translate to right 50 px
        g2.translate(50 / 0.4, 0);
        drawWindow(g2);

        // draw third window twice to demo different coordinate frames

        // set transform to saved matrix to return to "House" coordinates
        // since this window is drawn in house coordinates, it will be
        // transformed with the house
        g2.setTransform(save);
        drawWindow(g2, 0, -50, 45, 0.25);

        // set transform to identity to reset
        // this means third window is drawn in World Coordinates
        // and won't be transformed with the house
        g2.setTransform(new AffineTransform());

        // using function which has a model-to-world transform built in
        drawWindow(g2, getWidth()/2, 89, 45, 0.5);
    }

    // 100 x 100 house shape using Java Polygon structure
    // (model position is centred at top left corner)
    private Polygon houseShape = new Polygon(new int[] { -50, 50,  50,  0, -50},
            new int[] { 75,  75, -25, -75, -25}, 5);

    // draws 100 x 100 window shape centred at 0,0
    void drawWindow(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(-50, -50, 100, 100);
        g2.setColor(Color.WHITE);
        g2.fillRect(-40, -40, 35, 35);
        g2.fillRect(5, -40, 35, 35);
        g2.fillRect(-40, 5, 35, 35);
        g2.fillRect(5, 5, 35, 35);
    }

    // draws 100 x 100 window shape centred at 0,0
    void drawWindow(Graphics2D g2, double x, double y, double theta, double s) {

        // save the current g2 transform matrix
        AffineTransform save = g2.getTransform();

        // do the model to world transformation
        g2.translate(x, y);  // T
        g2.rotate(Math.toRadians(theta)); // R
        g2.scale(s, s);  // S

        // draws 100 x 100 window centred at 0,0
        drawWindow(g2);
        // reset the transform to what it was before we drew the shape
        g2.setTransform(save);
    }
}
