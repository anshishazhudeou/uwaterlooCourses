/*
* CS 349 Java Code Examples
*
* ListenerEvents.java       Binding events by creating a child object 
*                           which implements the listener interface.
*
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

import java.awt.*;

public class ListenerEvents extends JPanel  {
	
    public static void main(String[] args) {
    	ListenerEvents panel = new ListenerEvents();
        JFrame f = new JFrame("ListenerEvents"); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(200, 200); 
        f.setContentPane(panel);
        f.setVisible(true); 
    }

    ListenerEvents() {
        // need to add the listener to this panel 
        this.addMouseMotionListener(new MyMouseMotionListener());
    }
    
    // inner class listener
    class MyMouseMotionListener implements MouseMotionListener {

    	public void mouseDragged(MouseEvent e) {
    		x = e.getX();
    		y = e.getY();
    		repaint();	
    	}

    	public void mouseMoved(MouseEvent e) { /* no-op */ }
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