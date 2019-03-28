
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

// Create a simple form
// Adds a listener
public class BasicForm2 {

    public static void main(String[] args) {
        // create a window
        JFrame frame = new JFrame("Window Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a panel and add components
        JPanel panel = new JPanel();
        JButton button = new JButton("Ok");
        button.addMouseListener(new MyMouseListener());
        panel.add(button);

        // add panel to the window
        frame.add(panel);

        // set window behaviour and display it
        frame.setResizable(false);
        frame.setSize(200, 200);
        // frame.pack();
        frame.setVisible(true);
    }

    // create a custom listener for my ok button
    static class MyMouseListener implements MouseInputListener  {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(1);
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }

        @Override
        public void mouseDragged(MouseEvent e) { }

        @Override
        public void mouseMoved(MouseEvent e) { }
    }
}