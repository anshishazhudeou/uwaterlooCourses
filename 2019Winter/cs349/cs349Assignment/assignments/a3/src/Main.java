/*
    CS 349 Winter 2019 Assignment 3
    (c) 2019 Jeff Avery
 */

//import java.awt.*;
//import java.awt.datatransfer.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.io.IOException;
import javax.swing.*;

//public class Main extends JFrame implements ClipboardOwner {
public class Main {
//
//    JTextArea text;
//    static String STARTING_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
//            "empor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
//            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
//            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n" +
//            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
//            "empor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
//            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
//            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n" +
//            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
//            "empor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
//            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
//            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n" +
//            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
//            "empor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
//            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
//            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n";
//
//    // popup menu widget
//    class PopUpDemo extends JPopupMenu {
//
//        public PopUpDemo(){
//            // file
//            var fileMenu = new JMenu("File");
//            var newMenuItem = new JMenuItem("New");
//            newMenuItem.setToolTipText("Create a new document");
//            newMenuItem.addActionListener(e -> {
//                System.out.println("File-New");
//                doNew();
//            });
//            fileMenu.add(newMenuItem);
//
//            var exitMenuItem = new JMenuItem("Exit");
//            exitMenuItem.setToolTipText("Exit the application");
//            exitMenuItem.addActionListener(e -> {
//                System.out.println("File-Exit");
//                System.exit(1);
//            });
//            fileMenu.add(exitMenuItem);
//
//            // edit
//            var editMenu = new JMenu("Edit");
//            var cutMenuItem = new JMenuItem("Cut");
//            cutMenuItem.setToolTipText("Cut selection to the clipboard");
//            cutMenuItem.addActionListener(e -> {
//                System.out.println("Edit-Cut");
//                doCopy();
//                text.replaceSelection("");
//            });
//            editMenu.add(cutMenuItem);
//
//            var copyMenuItem = new JMenuItem("Copy");
//            copyMenuItem.setToolTipText("Copy selection to the clipboard");
//            copyMenuItem.addActionListener(e -> {
//                System.out.println("Edit-Copy");
//                doCopy();
//            });
//            editMenu.add(copyMenuItem);
//
//            var pasteMenuItem = new JMenuItem("Paste");
//            pasteMenuItem.setToolTipText("Paste from the clipboard");
//            pasteMenuItem.addActionListener(e -> {
//                System.out.println("Edit-Paste");
//                doPaste();
//            });
//            editMenu.add(pasteMenuItem);
//
//            // format
//            var formatMenu = new JMenu("Format");
//            var foregroundMenuItem = new JMenuItem("Foreground");
//            foregroundMenuItem.setToolTipText("Set colour");
//            foregroundMenuItem.addActionListener(e -> {
//                System.out.println("Colour-Foreground");
//                Color color = JColorChooser.showDialog(this, "Select a foreground colour", text.getForeground());
//                text.setForeground(color);
//            });
//            formatMenu.add(foregroundMenuItem);
//
//            var backgroundMenuItem = new JMenuItem("Background");
//            backgroundMenuItem.setToolTipText("Set colour");
//            backgroundMenuItem.addActionListener(e -> {
//                System.out.println("Colour-Background");
//                Color color = JColorChooser.showDialog(this, "Select a background colour", text.getBackground());
//                text.setBackground(color);
//            });
//            formatMenu.add(backgroundMenuItem);
//
//            // add to popup menu
//            add(fileMenu);
//            add(editMenu);
//            add(formatMenu);
//        }
//
//    }
//
//    // popup menu listener
//    class PopClickListener extends MouseAdapter {
//        public void mousePressed(MouseEvent e){
//            if (e.isPopupTrigger())
//                doPop(e);
//        }
//
//        public void mouseReleased(MouseEvent e){
//            if (e.isPopupTrigger())
//                doPop(e);
//        }
//
//        private void doPop(MouseEvent e){
//            PopUpDemo menu = new PopUpDemo();
//            menu.show(e.getComponent(), e.getX(), e.getY());
//        }
//    }
//
//    // functionality
//    private void doNew() {
//        text.setForeground(Color.BLACK);
//        text.setBackground(Color.WHITE);
//        text.setText(STARTING_STRING);
//    }
//
//    private void doCopy() {
//
//        // Get the system clipboard
//        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
//
//        // Create a transferable object encapsulating all the info for the copy
//        Transferable transferObject = new Transferable() {
//
//            private String textSelected = text.getSelectedText();
//
//            // Returns the copy data
//            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
//                System.out.println("  Transferable.getTransferData as " + flavor);
//                if (flavor.equals(DataFlavor.stringFlavor)) {
//                    return textSelected;
//                }
//                throw new UnsupportedFlavorException(flavor);
//            }
//
//            // Returns the set of data formats we can provide
//            public DataFlavor[] getTransferDataFlavors() {
//                System.out.println("  Transferable.getTransferDataFlavors");
//                return new DataFlavor[] { DataFlavor.stringFlavor };
//            }
//
//            // Indicates whether we can provide data in the specified format
//            public boolean isDataFlavorSupported(DataFlavor flavor) {
//                System.out.println("  Transferable.isDataFlavorSupported: " + flavor);
//                return flavor.equals(DataFlavor.stringFlavor);
//            }
//        };
//
//        // Now set the contents of the clipboard to our transferable object
//        // NOTE: The second argument "this" tells the system that this
//        //       object would like to be the owner of the clipboard.
//        //       As such, this object must implement the ClipboardOwner interface
//        System.out.println("COPY: set system clipboard to Transferable");
//        cb.setContents(transferObject, this);
//    }
//
//    private void doPaste() {
//
//        // Grab system clipboard
//        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//
//        System.out.println(String.format("PASTE: %d available flavours ... ",
//                systemClipboard.getAvailableDataFlavors().length));
//        for (DataFlavor f: systemClipboard.getAvailableDataFlavors()) {
//            System.out.println("  " + f.getHumanPresentableName() + "  " + f.toString());
//        }
//
//        // Check if we can get the data as a string
//        if (systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
//            System.out.println("DataFlavor.stringFlavor available");
//            try {
//                // Grab the data, set our text area to the data
//                String theText = (String)systemClipboard.getData(DataFlavor.stringFlavor);
//                text.replaceSelection(theText);
//            } catch (UnsupportedFlavorException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("DataFlavor.stringFlavor NOT available");
//        }
//    }
//
//    // required for clipboard management
//    public void lostOwnership(Clipboard clipboard, Transferable contents) {
//        System.out.println("Lost clipboard ownership");
//    }
//
//    // main entry point
//    public Main() {
//        // setup window and test area
//        setTitle("A3: Marking Menu");
//        setSize(800, 600);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//        text = new JTextArea(STARTING_STRING);
//        text.setMargin(new Insets(5, 5, 5, 5));
//        text.setFont(new Font("Serif", Font.PLAIN, 16));
//        text.setLineWrap(true);
//        text.setEditable(true);
//        text.setRequestFocusEnabled(true);
//        text.addMouseListener(new PopClickListener());
//
//        JScrollPane scroller = new JScrollPane(text);
//        setContentPane(scroller);
//    }

    public Main(){

    }

    public void runMVC() {
        Model model = new Model();
        View  view = new View();
        Controller controller = new Controller(model, view);
        view.addController(controller);
        view.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            var ex = new Main();
//            ex.setVisible(true);
            ex.runMVC();
        });
    }
}