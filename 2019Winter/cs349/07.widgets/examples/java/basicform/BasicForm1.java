
import javax.swing.*;

// Create a simple form
public class BasicForm1 {
    public static void main(String[] args) {
        // create a window
        JFrame frame = new JFrame("Window Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a panel and add components
        JPanel panel = new JPanel();
        JButton button = new JButton("Ok");
        panel.add(button);

        // add panel to the window
        frame.add(panel);

        // set window behaviour and display it
        frame.setResizable(false);
        frame.setSize(200, 200);
        // frame.pack();
        frame.setVisible(true);
    }
}

