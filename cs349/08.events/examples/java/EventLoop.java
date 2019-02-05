/*
* CS 349 Java Code Examples
*
* EventLoop.Java            Binding events by tapping into the low-level
*                             java event queue.
*
*/
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.event.MouseEvent;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class EventLoop extends JPanel  {
	
	EventLoop() 
			throws InterruptedException, InvocationTargetException  {

        EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                EventQueue eq = Toolkit.getDefaultToolkit().getSystemEventQueue();
                // replace the current event queue with MyEventQueue
                eq.push(new MyEventQueue());
                System.out.println("Run");
            }
        });	
    }

    public static void main(String[] args)
    		throws InterruptedException, InvocationTargetException {

    	EventLoop panel = new EventLoop();
        JFrame f = new JFrame("EventLoop"); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(200, 200); 
        f.setContentPane(panel);
        f.setVisible(true); 
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
    

    // kind of like an event loop
    private class MyEventQueue extends EventQueue {
    	// mouse events come in here
        public void dispatchEvent(AWTEvent e) {
            //System.out.println("dispatchEvent " + e.getID() );
            if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
                MouseEvent me = (MouseEvent)e;
    			x = me.getX();
    			y = me.getY();
                System.out.println("(" + x + "," + y + ")");
                repaint();
    		}
            super.dispatchEvent(e);
        }
    }
}