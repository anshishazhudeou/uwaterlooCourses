/*
* CS 349 Java Code Examples
*
* CompositionOrder        Demo of different concatenation orders of matrix transforms.
*					 Click the window to change the order.
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
import java.awt.BorderLayout;
import java.awt.FlowLayout;

// create the window and run the demo
public class CompositionOrder {
	
    public static void main(String[] args) {
        // create the window        
    	Canvas canvas = new Canvas();
        JFrame f = new JFrame("CompositionOrder"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500); // window size
        f.setContentPane(canvas); // add canvas to jframe   
        f.setBackground(Color.WHITE);       
        f.setVisible(true); // show the window
    }
} 

class Canvas extends JComponent {

	// rotate
	double theta = 30;
	// translate
	double tx = 100;
	double ty = 0;
	// scale
	double sx = 2;
	double sy = 1.2;
	
	// the house shape (model position is centred at top left corner)
	private Polygon shape = new Polygon(new int[] { -50, 50,  50, 	0, -50}, 
										new int[] { 75,  75, -25, -75, -25}, 5);

	// a larger font for displaying the concatenation order
	private Font font = new Font("SansSerif", Font.PLAIN, 30);

	// the concatenation order
    private int order = 0;

	Canvas() {
		
		// only mouse clicked events
		addMouseListener(new MouseAdapter() { 
	          public void mouseClicked(MouseEvent me) { 
	        	  order = (order + 1) % 6;
	        	  repaint();
	          } 
	        }); 
		
		System.out.println("click to change transformation composition order");
	}
    
    // custom graphics drawing 
    public void paintComponent(Graphics g) {
    	super.paintComponent(g); // JPanel paint
    	Graphics2D g2 = (Graphics2D)g;

    	// make a centred grid
    	int gridSize = 250;
    	g2.translate((this.getWidth() - gridSize) / 2, (this.getHeight() - gridSize) / 2);
    	drawGrid(g2, gridSize, gridSize, gridSize / 10);
    	
    	// save the current transform matrix 
    	AffineTransform M = g2.getTransform();
    	
    	// draw the original shape in "model" coordinates
    	g2.setColor(Color.BLACK);
    	g2.setStroke(new BasicStroke(3));
    	g2.drawPolygon(shape.xpoints, shape.ypoints, shape.npoints);
    	// mark 0, 0 too
    	g2.setStroke(new BasicStroke(1)); 
    	g2.drawOval(-5, -5, 10, 10);
    	
    	// create transformation matrices
    	AffineTransform R = 
            AffineTransform.getRotateInstance(Math.toRadians(theta));
    	AffineTransform T = 
            AffineTransform.getTranslateInstance(tx, ty);
    	AffineTransform S = 
            AffineTransform.getScaleInstance(sx, sy);
    	
    	// concatenate the matrices in 1 of 6 orders
    	String s = "p'=";
    	switch (order)
    	{
    	case 0:
	    	s += "TRS";
	    	g2.transform(T);
	    	g2.transform(R);
	    	g2.transform(S);
	    	break;	
	    	
    	case 1:
	    	s += "TSR";
	    	g2.transform(T);
	    	g2.transform(S);
	    	g2.transform(R);
	    	break;
	    	
    	case 2:
	    	s += "RST";
	    	g2.transform(R);
	    	g2.transform(S);
	    	g2.transform(T);
	    	break;		

    	case 3:
	    	s += "RTS";
	    	g2.transform(R);
	    	g2.transform(T);
	    	g2.transform(S);
	    	break;
	    	
    	case 4:
	    	s += "SRT";
	    	g2.transform(S);
	    	g2.transform(R);
	    	g2.transform(T);
	    	break;	
	    	
    	case 5:
	    	s += "STR";
	    	g2.transform(S);
	    	g2.transform(T);
	    	g2.transform(R);
	    	break;		    	
    	}
    	s += "p";
    	
    	// the shape will get transformed into "world" coordinates
    	g2.setColor(Color.RED);  
    	g2.setStroke(new BasicStroke(3)); 	
    	g2.drawPolygon(shape.xpoints, shape.ypoints, shape.npoints);
    	// mark 0, 0 too
    	g2.setStroke(new BasicStroke(1)); 
    	g2.drawOval(-5, -5, 10, 10);   	
    	
    	// reset to transform before we did the T, R, and S
    	// so we can draw the text
    	g2.setTransform(M);
    	
    	// display the order text
    	g2.setColor(Color.BLACK);
    	g2.setFont(font);
    	g2.drawString(s, 0, gridSize + 50);
    }


     private void drawGrid(Graphics2D g2, int w, int h, int s) {

        // Draw grid
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.GRAY.brighter());
        // horizontal lines
        for(int i = 0; i <= h; i += s) {
            g2.drawLine(0, i, w, i);
        }
        // vertical lines
        for(int i = 0; i <= w; i += s) {
            g2.drawLine(i, 0, i, h);
        }
    }

}
