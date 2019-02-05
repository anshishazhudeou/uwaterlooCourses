/*
* CS 349 Java Code Examples
*
* TransformHitTest - check mouse against transformed shape
*
*/

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math.*;
import java.awt.event.*;

// create the window and run the demo
public class TransformHittest {
    
    public static void main(String[] args) {
        // create the window        
        Canvas canvas = new Canvas();
        JFrame f = new JFrame("TransformHitTest"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500); // window size
        f.setContentPane(canvas); // add canvas to jframe   
        f.setBackground(Color.WHITE);       
        f.setVisible(true); // show the window
    }
} 

class Canvas extends JComponent {

    Point M = new Point();  // mouse point

    AffineTransform AT;     // original matrix
    AffineTransform AT1;    // transform matrix for shape1
    AffineTransform AT2;    // transform matrix for shape2
    
    // the house shape (model position is centred at top left corner)
    private Polygon shape = new Polygon(new int[] { -50, 50,  50,   0, -50}, 
                                        new int[] { 75,  75, -25, -75, -25}, 5);

    Canvas() {
        // create transformation matrix for shape1
        AT1 = new AffineTransform();
        AT1.translate(400, 200);
        AT1.rotate(Math.toRadians(30));

        // create another transformation matrix for shape2
        AT2 = new AffineTransform();
        AT2.translate(200, 300);
        AT2.rotate(Math.toRadians(30));
        AT2.scale(2, 2);

        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                M.x = e.getX();
                M.y = e.getY();
                repaint();
            }
        });         
    }
    
    // custom graphics drawing 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        // save the current transform matrix 
        AffineTransform AT = g2.getTransform();
        
        // draw the original shape in "model" coordinates around origin
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawPolygon(shape.xpoints, shape.ypoints, shape.npoints);

        // Shape1
        // original matrix x AT1 matrix --> applies transformations from AT1
        AffineTransform tempAT1 = new AffineTransform(AT);
        tempAT1.concatenate(AT1);
        g2.setTransform(tempAT1);

        g2.setColor(Color.RED);  
        g2.drawPolygon(shape.xpoints, shape.ypoints, shape.npoints);

        // hit test
        try{
            // create an inverse matrix of AT1
            // apply the inverse transformation to the mouse position
            AffineTransform IAT = AT1.createInverse();
            Point MT = new Point();
            IAT.transform(M, MT);

            // check if original shape contains transformed mouse position
            if (shape.contains(MT.x, MT.y)) {
                g2.setColor(Color.RED);
            } else {
                g2.setColor(Color.WHITE);  
            }
            g2.fillPolygon(shape);
        } catch (NoninvertibleTransformException e){
            System.out.println("Clearly, someone else broke my code. Cannot invert matrix");
        }

        // Shape2
        // original matrix x AT2 matrix --> applies transformations from AT2
        AffineTransform tempAT2 = new AffineTransform(AT);
        tempAT2.concatenate(AT2);
        g2.setTransform(tempAT2);

        g2.setColor(Color.BLUE);  
        g2.drawPolygon(shape.xpoints, shape.ypoints, shape.npoints);

        // hit test
        try {
            // create an inverse matrix of AT2
            // apply the inverse transformation to the mouse position
            AffineTransform IAT = AT2.createInverse();
            Point MT = new Point();
            IAT.transform(M, MT);

            // check if original shape contains transformed mouse position
            if (shape.contains(MT.x, MT.y))
                g2.setColor(Color.BLUE);
            else
                g2.setColor(Color.WHITE);  
            g2.fillPolygon(shape);
        } catch (NoninvertibleTransformException e){
            System.out.println("For some inconceivable reason, cannot invert matrix");
        }
        
        // reset to original transform matrix
        g2.setTransform(AT);
    }
}
