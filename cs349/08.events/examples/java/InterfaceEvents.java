/*
* CS 349 Java Code Examples
*
* InterfaceEvents.java      Binding events by implementing event listener
*                           interface.
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.*;

public class InterfaceEvents extends JPanel implements MouseMotionListener  {
	
	InterfaceEvents() {
		// need to add the listener to this panel 
		// (panel and listener are the same in this case)
        this.addMouseMotionListener(this);
	}

    public static void main(String[] args) {
    	InterfaceEvents panel = new InterfaceEvents();
        JFrame f = new JFrame("InterfaceEvents"); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(200, 200); 
        f.setContentPane(panel);
        f.setVisible(true); 
    }
    
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		repaint();	
	}

	public void mouseMoved(MouseEvent e) { /* no-op */  }
    
    int x;
    int y;
    int size = 50;

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 
        g2.setColor(Color.RED);
		g2.fillOval(x - size/2, y - size/2, size, size);
    }
}