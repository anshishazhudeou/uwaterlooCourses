/*
* CS 349 Java Code Examples
*
* InheritanceEvents.java    Binding events by extending base class with
*                           event callback methods.
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.*;

public class InheritanceEvents extends JPanel  {

    public static void main(String[] args) {
    	InheritanceEvents panel = new InheritanceEvents();
        JFrame f = new JFrame("InheritanceEvents"); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(200, 200); 
        f.setContentPane(panel);
        f.setVisible(true); 
        
        // enable events for this JPanel
    	panel.enableEvents(MouseEvent.MOUSE_MOTION_EVENT_MASK);
    }
    
	protected void processMouseMotionEvent(MouseEvent e)
	{
		// only detects when button is down WHILE also moving
		if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
			x = e.getX();
			y = e.getY();
            repaint();
		}
	}

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