
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

// Create a simple form
// Converts adapter to anonymous inner class
public class BasicForm4 {

    public static void main(String[] args) {
        // create a window
        JFrame frame = new JFrame("Window Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a panel and add components
        JPanel panel = new JPanel();
        JButton button = new JButton("Ok");
        button.addMouseListener(new MouseAdapter() { 
                public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                    System.exit(1);
        }});
        panel.add(button);

        // add panel to the window
        frame.add(panel);

        // set window behaviour and display it
        frame.setResizable(false);
        frame.setSize(200, 200);
        // frame.pack();
        frame.setVisible(true);
    }

// We do not need to implement a custom adapter
/*    // create a custom adapter from MouseAdapter base class
    static class MyMouseAdapter extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            System.exit(1);
        }
    }*/
}

