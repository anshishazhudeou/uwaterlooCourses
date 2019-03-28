import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.util.Vector;

// JComponent is a base class for custom components 
public class SimpleDraw4 extends JComponent {

    public static void main(String[] args) {
        JFrame f = new JFrame("SimpleDraw"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400); // window size
        
        SimpleDraw4 canvas = new SimpleDraw4();
        f.setContentPane(canvas); // add canvas to jframe
        f.setVisible(true); // show the window
    }

    // Object properties
    private int mouseX;
    private int mouseY;

    // Constructor for SimpleDraw
    public SimpleDraw4() {

        // this is an example of an anonymous inner class
    	this.addMouseMotionListener( new MouseAdapter() {
          public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();
          }
	});
    }

    // custom graphics drawing 
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; // cast to get 2D drawing methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  // antialiasing look nicer
        					RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(32)); // 32 pixel thick stroke
        g2.setColor(Color.BLUE); // make it blue
        g2.drawLine(0, 0, getWidth(), getHeight());  // draw line 
        g2.setColor(Color.RED);
        g2.drawLine(getWidth(), 0, 0, getHeight());  

	    String label = "Mouse at (" + mouseX + ", " + mouseY + ")";
        g2.setColor(Color.BLACK);
	    g2.drawString(label, 130, 40);
    }
}
