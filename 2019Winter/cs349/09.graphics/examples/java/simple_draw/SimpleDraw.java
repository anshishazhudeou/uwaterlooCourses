import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;



// JComponent is a base class for custom components 
public class SimpleDraw extends JComponent {

    public static void main(String[] args) {
        JFrame f = new JFrame("SimpleDraw"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400); // window size

        SimpleDraw canvas = new SimpleDraw();
        f.setContentPane(canvas); // add canvas to jframe
        f.setVisible(true); // show the window
    }

    // Constructor for SimpleDraw
    public SimpleDraw() {
    }

    // custom graphics drawing 
    // this is a method that we overrode from the JComponent class
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;               // 2D drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        					RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(32));           // 32 pixels wide
        g2.setColor(Color.BLUE);                     // make it blue
        g2.drawLine(0, 0, getWidth(), getHeight());  // draw line 
        g2.setColor(Color.RED);
        g2.drawLine(getWidth(), 0, 0, getHeight());  
    }
}

