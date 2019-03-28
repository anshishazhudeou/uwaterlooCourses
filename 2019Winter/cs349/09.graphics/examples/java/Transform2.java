/*
* CS 349 Java Code Examples
*
* Transform2  Shows how to use Graphics2D matrix tranformations to transform a shape model. 
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
public class Transform2 {
	
    public static void main(String[] args) {
        // create the window        
    	Canvas canvas = new Canvas();
        JFrame f = new JFrame("Transform2"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400); // window size
        f.setContentPane(canvas); // add canvas to jframe         
        f.setVisible(true); // show the window
    }
} 

class Canvas extends JComponent {
	
	// the house shape (model position is centred at top left corner)
	private Polygon shape = new Polygon(new int[] { -50, 50,  50, 	0, -50}, 
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
    	
    	// the shape will get transformed when rendered
		g2.translate(M.x, M.y);
		g2.scale(2, 1);
		g2.rotate(Math.toRadians(45));

     	g2.setStroke(new BasicStroke(3)); 
    	g2.drawPolygon(shape.xpoints, shape.ypoints, shape.npoints);
    } 
}
