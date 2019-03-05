import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.List;

class ScrollPaneDemo {

    public static void main(String[] args) {
        new ScrollPaneDemo();
    }

    ScrollPaneDemo() {
        // frame
        JFrame main = new JFrame();
        main.setMinimumSize(new Dimension(400, 300));
        main.setPreferredSize(new Dimension(400, 400));
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);

        // main panel
        JPanel mp = new JPanel();
        mp.setLayout(new FlowLayout());
        mp.setPreferredSize(new Dimension(400,5100));   // 250 height * 20 panels + padding

        // content panels
        List<JPanel> panels = new ArrayList();
        for (int i = 0; i < 20; ++i) {
            panels.add(new JPanel());
            panels.get(i).setPreferredSize(new Dimension(250, 250));
            panels.get(i).setBackground(Color.BLUE);
            mp.add(panels.get(i));
        }

        JScrollPane jp = new JScrollPane(mp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jp.getVerticalScrollBar().setUnitIncrement(15); // speedup scrolling
        main.add(jp);
        main.pack();
    }
}