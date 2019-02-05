import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.*;

public class Main extends JPanel {

    public static void main(String[] args){
        JFrame frame = new JFrame("NoMvc");
        frame.getContentPane().add(new Main());
        frame.setPreferredSize(new Dimension(300,300));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    int counter = 0;

    void increaseCounter() {
        if (counter < 5) {
            counter++;
            String s = Integer.toString(counter);
            button.setText(s);
            // just displays an 'X' for each counter value
            s = "";
            for (int i = 0; i< counter; i++) s = s + "X";
            label.setText(s);
        }
    }

    JButton button;
    JLabel label;

    public Main() {

        this.setLayout(new GridLayout(2,1));

        JPanel topPanel = new JPanel();
        button = new JButton("?");
        button.setMaximumSize(new Dimension(100, 50));
        button.setPreferredSize(new Dimension(100, 50));
        topPanel.setLayout(new GridBagLayout());
        topPanel.add(button, new GridBagConstraints());
		button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    increaseCounter();
                }
            });
		this.add(topPanel);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel("");
        bottomPanel.add(label);
        bottomPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    increaseCounter();
                }
            });
        this.add(bottomPanel);
    }
}