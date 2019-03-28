/*
* CS 349 Java Code Examples
*
* AdapterEvents.java        Binding events by creating a child listener 
*                           adapter object.
*
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.awt.*;

public class AdapterEvents extends JPanel  {
	
    public static void main(String[] args) {
    	AdapterEvents panel = new AdapterEvents();
        JFrame f = new JFrame("AdapterEvents"); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(200, 200); 
        f.setContentPane(panel);
        f.setVisible(true); 
    }

    AdapterEvents() {
        this.addMouseMotionListener(new MyMouseMotionListener());
    }
    
    class MyMouseMotionListener extends MouseMotionAdapter {

    	public void mouseDragged(MouseEvent e) {
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