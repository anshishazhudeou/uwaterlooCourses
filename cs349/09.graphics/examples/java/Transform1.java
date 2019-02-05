/*
* CS 349 Java Code Examples
*
* Transform1  Shows how to "manually" transform a shape model. NOTE: in practice,
              you don't want do this. Use Graphics2D matrix tranformations instead.
*
*/
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.vecmath.*;
import java.lang.Math.*;
import java.util.Random;
import java.awt.event.*;

// create the window and run the demo
public class Transform1 {
	
    public static void main(String[] args) {
        // create the window        
    	Canvas canvas = new Canvas();
        JFrame f = new JFrame("Transform1"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400); // window size
        f.setContentPane(canvas); // add canvas to jframe         
        f.setVisible(true); // show the window
    }
} 

class Canvas extends JComponent {
	
    // the house shape model (position is centred at top left corner)
    private Polygon shape = 
        new Polygon(new int[] { -50, 50,  50,   0, -50}, 
                    new int[] { 75,  75, -25, -75, -25}, 5);

	Point2d M = new Point2d();

	Canvas() {
		// events
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                M.x = e.getX();
                M.y = e.getY();
                repaint();
            }});
	}
    
    // custom graphics drawing 
    public void paintComponent(Graphics g) {
    	super.paintComponent(g); // JPanel paint
    	Graphics2D g2 = (Graphics2D)g;
    	
    	// the shape will get transformed into "world" coordinates

		// transform by hand 
		// get copy of shape
		Polygon transShape = 
            new Polygon(shape.xpoints, shape.ypoints, shape.npoints);


		rotate(transShape, 45);
        scale(transShape, 2, 1);        
		translate(transShape, M.x, M.y);

     	g2.setStroke(new BasicStroke(3)); 
    	g2.drawPolygon(transShape.xpoints, transShape.ypoints, transShape.npoints);
    }

    // non-matrix translate
    void translate(Polygon s, double tx, double ty) {
    	for (int i = 0; i < s.npoints; i++) {
			s.xpoints[i] += tx;
			s.ypoints[i] += ty;
		}
    }

    // non-matrix scale
    void scale(Polygon s, double sx, double sy) {
    	for (int i = 0; i < s.npoints; i++) {
			s.xpoints[i] *= sx;
			s.ypoints[i] *= sy;
		}
    }   

    // no-matrix rotate
    void rotate(Polygon s, double theta) {
        double rad = Math.toRadians(theta);
    	for (int i = 0; i < s.npoints; i++) {
            int x = s.xpoints[i];
            int y = s.ypoints[i];
			s.xpoints[i] = (int)((double)x * Math.cos(rad) - y * Math.sin(rad));
			s.ypoints[i] = (int)((double)x * Math.sin(rad) + y * Math.cos(rad));
		}
    }   

}
