import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View extends JFrame implements ClipboardOwner {
    private JTextArea text;

    private Controller controller;

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("Lost clipboard ownership");
    }

    public void addController(Controller controller){
        this.controller = controller;
        doNew();
    }

    // popup menu widget
    class PopUpDemo extends JPopupMenu {

        public PopUpDemo(){
            // file
            var fileMenu = new JMenu("File");
            var newMenuItem = new JMenuItem("New");
            newMenuItem.setToolTipText("Create a new document");
            newMenuItem.addActionListener(e -> {
                System.out.println("File-New");
                doNew();
            });
            fileMenu.add(newMenuItem);

            var exitMenuItem = new JMenuItem("Exit");
            exitMenuItem.setToolTipText("Exit the application");
            exitMenuItem.addActionListener(e -> {
                System.out.println("File-Exit");
                System.exit(1);
            });
            fileMenu.add(exitMenuItem);

            // edit
            var editMenu = new JMenu("Edit");
            var cutMenuItem = new JMenuItem("Cut");
            cutMenuItem.setToolTipText("Cut selection to the clipboard");
            cutMenuItem.addActionListener(e -> {
                System.out.println("Edit-Cut");
                doCopy();
                text.replaceSelection("");
            });
            editMenu.add(cutMenuItem);

            var copyMenuItem = new JMenuItem("Copy");
            copyMenuItem.setToolTipText("Copy selection to the clipboard");
            copyMenuItem.addActionListener(e -> {
                System.out.println("Edit-Copy");
                doCopy();
            });
            editMenu.add(copyMenuItem);

            var pasteMenuItem = new JMenuItem("Paste");
            pasteMenuItem.setToolTipText("Paste from the clipboard");
            pasteMenuItem.addActionListener(e -> {
                System.out.println("Edit-Paste");
                doPaste();
            });
            editMenu.add(pasteMenuItem);

            // format
            var formatMenu = new JMenu("Format");
            var foregroundMenuItem = new JMenuItem("Foreground");
            foregroundMenuItem.setToolTipText("Set colour");
            foregroundMenuItem.addActionListener(e -> {
                System.out.println("Colour-Foreground");
                Color color = JColorChooser.showDialog(this, "Select a foreground colour", text.getForeground());
                text.setForeground(color);
            });
            formatMenu.add(foregroundMenuItem);

            var backgroundMenuItem = new JMenuItem("Background");
            backgroundMenuItem.setToolTipText("Set colour");
            backgroundMenuItem.addActionListener(e -> {
                System.out.println("Colour-Background");
                Color color = JColorChooser.showDialog(this, "Select a background colour", text.getBackground());
                text.setBackground(color);
            });
            formatMenu.add(backgroundMenuItem);

            // add to popup menu
            add(fileMenu);
            add(editMenu);
            add(formatMenu);
        }

    }

    class MarkingMenuDemo extends MarkingMenu {

        public MarkingMenuDemo(){
            // file
            var fileMenu = new JMenu("File");
            var newMenuItem = new JMenuItem("New");
            newMenuItem.setToolTipText("Create a new document");
            newMenuItem.addActionListener(e -> {
                System.out.println("File-New");
                doNew();
            });
            fileMenu.add(newMenuItem);

            var exitMenuItem = new JMenuItem("Exit");
            exitMenuItem.setToolTipText("Exit the application");
            exitMenuItem.addActionListener(e -> {
                System.out.println("File-Exit");
                System.exit(1);
            });
            fileMenu.add(exitMenuItem);

            // edit
            var editMenu = new JMenu("Edit");
            var cutMenuItem = new JMenuItem("Cut");
            cutMenuItem.setToolTipText("Cut selection to the clipboard");
            cutMenuItem.addActionListener(e -> {
                System.out.println("Edit-Cut");
                doCopy();
                text.replaceSelection("");
            });
            editMenu.add(cutMenuItem);

            var copyMenuItem = new JMenuItem("Copy");
            copyMenuItem.setToolTipText("Copy selection to the clipboard");
            copyMenuItem.addActionListener(e -> {
                System.out.println("Edit-Copy");
                doCopy();
            });
            editMenu.add(copyMenuItem);

            var pasteMenuItem = new JMenuItem("Paste");
            pasteMenuItem.setToolTipText("Paste from the clipboard");
            pasteMenuItem.addActionListener(e -> {
                System.out.println("Edit-Paste");
                doPaste();
            });
            editMenu.add(pasteMenuItem);

            // format
            var formatMenu = new JMenu("Format");
            var foregroundMenuItem = new JMenuItem("Foreground");
            foregroundMenuItem.setToolTipText("Set colour");
            foregroundMenuItem.addActionListener(e -> {
                System.out.println("Colour-Foreground");
                Color color = JColorChooser.showDialog(this, "Select a foreground colour", text.getForeground());
                text.setForeground(color);
            });
            formatMenu.add(foregroundMenuItem);

            var backgroundMenuItem = new JMenuItem("Background");
            backgroundMenuItem.setToolTipText("Set colour");
            backgroundMenuItem.addActionListener(e -> {
                System.out.println("Colour-Background");
                Color color = JColorChooser.showDialog(this, "Select a background colour", text.getBackground());
                text.setBackground(color);
            });
            formatMenu.add(backgroundMenuItem);

            // add to popup menu
            add(fileMenu);
            add(editMenu);
            add(formatMenu);
        }

    }

    // popup menu listener
    class PopClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e){
            if (e.isPopupTrigger())
                doPop(e);
        }

        public void mouseReleased(MouseEvent e){
            if (e.isPopupTrigger())
                doPop(e);
        }

        private void doPop(MouseEvent e){
//            View.PopUpDemo menu = new View.PopUpDemo();
//            menu.show(e.getComponent(), e.getX(), e.getY());
            View.MarkingMenuDemo menu = new View.MarkingMenuDemo();
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public View() {
        setTitle("A3: Marking Menu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        text = new JTextArea();
        text.setMargin(new Insets(5, 5, 5, 5));
        text.setFont(new Font("Serif", Font.PLAIN, 16));
        text.setLineWrap(true);
        text.setEditable(true);
        text.setRequestFocusEnabled(true);
        text.addMouseListener(new PopClickListener());

        JScrollPane scroller = new JScrollPane(text);
        setContentPane(scroller);
    }

    private void doCopy() {

        // Create a transferable object encapsulating all the info for the copy
        Transferable transferObject = new Transferable() {

            private String textSelected = text.getSelectedText();

            // Returns the copy data
            public Object getTransferData(
                DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                System.out.println("  Transferable.getTransferData as " + flavor);
                if (flavor.equals(DataFlavor.stringFlavor)) {
                    return textSelected;
                }
                throw new UnsupportedFlavorException(flavor);
            }

            // Returns the set of data formats we can provide
            public DataFlavor[] getTransferDataFlavors() {
                System.out.println("  Transferable.getTransferDataFlavors");
                return new DataFlavor[] { DataFlavor.stringFlavor };
            }

            // Indicates whether we can provide data in the specified format
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                System.out.println("  Transferable.isDataFlavorSupported: " + flavor);
                return flavor.equals(DataFlavor.stringFlavor);
            }
        };
        controller.doCopy(transferObject, this);
    }

    private void doPaste() {
        String theText = controller.doPaste();
        if (theText != null) {
            text.replaceSelection(theText);
        }
    }

    // functionality
    private void doNew() {
        text.setForeground(Color.BLACK);
        text.setBackground(Color.WHITE);
        text.setText(controller.initMsg());
    }


}
