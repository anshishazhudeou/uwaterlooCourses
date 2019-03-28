/*
* CS 349 Java Code Examples
*
* BarExercise        Demo of multiple transformation exercise.
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
public class BarExercise {
	
    public static void main(String[] args) {
        // create the window        
    	Canvas canvas = new Canvas();
        JFrame f = new JFrame("BarExercise"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500); // window size
        f.setContentPane(canvas); // add canvas to jframe    
        f.setBackground(Color.WHITE);     
        f.setVisible(true); // show the window
    }
} 

class Canvas extends JComponent {

	int step = 0;
  boolean debug = true;

	Canvas() {

		// only mouse clicked events
		addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent me) { 
	          	step ++;
	        	repaint();
            } 
        }); 
	}
    
    // custom graphics drawing 
    public void paintComponent(Graphics g) {
    	super.paintComponent(g); // JPanel paint
    	Graphics2D g2 = (Graphics2D)g;

    	// make a centred grid
    	int gridSize = 250;
    	g2.translate((this.getWidth() - gridSize) / 2, (this.getHeight() - gridSize) / 2);
    	drawGrid(g2, gridSize, gridSize, gridSize / 10);
    	
    	// draw the original shape in "model" coordinates
    	g2.setColor(Color.BLACK);
    	drawBar(g2, 50, 100, 150, 100);

    	// save the current transform matrix 
    	AffineTransform M = g2.getTransform();    	

    	// transfrom at one time
    	if (!debug) {
    		g2.translate(50, 100);
	    	g2.rotate(Math.toRadians(30));
	    	g2.translate(-50, -100);

	    	g2.setColor(Color.BLUE.darker());
	    	drawBar(g2, 50, 100, 150, 100);	

	    // demo the steps
	    } else {
	    	g2.setColor(Color.BLUE.darker());
    		switch (step % 4) {

    		case 1:
        		g2.translate(-50, -100);
    	    	drawBar(g2, 50, 100, 150, 100); 
    	    	g2.setTransform(M);
    	    	break;
    		case 2:
    	    	g2.rotate(Math.toRadians(30));
    	    	g2.translate(-50, -100);
    	    	drawBar(g2, 50, 100, 150, 100);
    	    	g2.setTransform(M);
    	    	break;
    		case 3:
    	    	g2.translate(50, 100);
    	    	g2.rotate(Math.toRadians(30));
    	    	g2.translate(-50, -100);
    	    	drawBar(g2, 50, 100, 150, 100);	
    	    	g2.setTransform(M);
    	    	break;
    		}
    	} 
    }

    private void drawBar(Graphics2D g2, int x1, int y1, int x2, int y2) {
    	int d = 12; 
    	g2.setStroke(new BasicStroke(4));
    	g2.drawLine(x1, y1, x2, y2);
    	g2.fillOval(x1 - d/2, y1 - d/2, d, d);
    	g2.fillOval(x2 - d/2, y2 - d/2, d, d);
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
