import javax.swing.*;

// Create a simple form
public class Main {
    A wo;
    int b = wo.a;
    public static void main(String[] args) {
        // create a window
        JFrame frame = new JFrame("Doodle (l62zhou)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // set window behaviour and display it
        frame.setResizable(true);
        frame.setSize(800, 600);

        // set the menubar for the current frame
        frame.setJMenuBar(createMenuBar());

        // frame.packtidier();
        frame.setVisible(true);


    }

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // create two menus File and Edit
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        // create menuItem New, Open, Exit and add them to File menu
        JMenuItem New = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem exit= new JMenuItem("Exit");
        file.add(New);
        file.add(open);
        file.add(exit);
        // create menuItem Cut, Copy, Paste and add them to Edit menu
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste= new JMenuItem("Paste");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        // add file and edit menu to the menuBar
        menuBar.add(file);
        menuBar.add(edit);
        return menuBar;
    }
}
