/*
* CS 349 Java Code Examples
*
* ClosestPoint    Uses two methods to compute distance from 
                      mouse to closest point on line. Double click
                      to generate new line.
*
*/
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.vecmath.*;
import java.lang.Math.*;
import java.util.Random;

// create the window and run the demo
public class ClosestPoint extends JPanel {

	Point P0 = new Point();
	Point P1 = null;
	Point M = new Point(); // mouse point

    public static void main(String[] args) {
        // create the window        
        ClosestPoint canvas = new ClosestPoint();
        JFrame f = new JFrame("ClosestPoint"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400); // window size
        f.setContentPane(canvas); // add canvas to jframe    
        f.setVisible(true); // show the window
    }    

	ClosestPoint() {     
		setBackground(Color.WHITE);
        // add listeners
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent arg0) {                
                // generate new line on double click
                if (arg0.getClickCount() == 2)  
                    randomLine();
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseMoved(MouseEvent arg0) {
                // TODO Auto-generated method stub
                M.x = arg0.getX();
                M.y = arg0.getY();
                repaint();                
            }            
        });
    }
    
    // custom graphics drawing 
    public void paintComponent(Graphics g) {
    	super.paintComponent(g); // JPanel paint
    	Graphics2D g2 = (Graphics2D)g;

        if (P1 == null) {
            P1 = new Point();
            randomLine();
        }
    	
    	// antiliasing
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    	        RenderingHints.VALUE_ANTIALIAS_ON);
    	
    	g2.setStroke(new BasicStroke(2));
    	
    	// draw line
    	g2.setColor(Color.BLACK);
    	g2.drawLine(P0.x, P0.y, P1.x, P1.y);
    	
    	// draw mouse point
    	g2.setColor(Color.BLACK);
    	int s = 7;
    	g2.drawOval(M.x - s, M.y - s, 2 * s, 2 * s );
    	
    	// get closest point using the vector projection method
    	Point2d M2 = new Point2d(M.x, M.y);
		Point2d I1 = closestPoint(M2, 
				    			new Point2d(P0.x, P0.y),
				    			new Point2d(P1.x, P1.y));
	 
    	// draw closest point
    	g2.setColor(Color.RED);
    	g2.drawOval((int)I1.x - s, (int)I1.y - s, 2 * s, 2 * s );
    	
    	// use that to get distance
		double d1 = M2.distance(I1);

    	// get distance using Java2D method
    	double d2 = Line2D.ptSegDist(P0.x, P0.y, P1.x, P1.y, M.x, M.y);
    	
    	g2.setColor(Color.BLACK);
    	g2.drawString(String.format("%.1f %.1f", d1, d2), M.x + 10,M.y);
    }
    
    // find closest point using projection method 
    static Point2d closestPoint(Point2d M, Point2d P0, Point2d P1) {
    	Vector2d v = new Vector2d();
    	v.sub(P1,P0); // v = P1 - P0
    	
    	// early out if line is less than 1 pixel long
    	if (v.lengthSquared() < 0.5)
    		return P0;
    	
    	Vector2d u = new Vector2d();
    	u.sub(M,P0); // u = M - P1

    	// scalar of vector projection ...
    	double s = u.dot(v) / v.dot(v); 
    	
    	// find point for constrained line segment
    	if (s < 0) 
    		return P0;
    	else if (s > 1)
    		return P1;
    	else {
    		Point2d I = P0;
        	Vector2d w = new Vector2d();
        	w.scale(s, v); // w = s * v
    		I.add(w); // I = P0 + w
    		return I;
    	}
    }
    
    // random numbers
    static Random rand = new Random();
    
    int random(int min, int max) {
    	return rand.nextInt(max - min + 1) + min;
    }
    
    void randomLine()
    {
		// create random line
		int m = 50; // margin
		P0.x = m;
		P0.y = random(m,  getHeight() - m);
		P1.x = getWidth() - m;
		P1.y = random(m, getHeight() - m);	
    }

}
