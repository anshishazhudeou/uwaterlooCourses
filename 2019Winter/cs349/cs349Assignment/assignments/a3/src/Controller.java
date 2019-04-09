import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Controller {
   private Model model;
   private View view;

   public Controller(Model model, View view) {
       this.model = model;
       this.view = view;
   }

    public String initMsg() {
        model.init();
        return model.getStrText();
    }

    public  void doCopy(Transferable transferObject, ClipboardOwner owner) {
        // Get the system clipboard
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Now set the contents of the clipboard to our transferable object
        // NOTE: The second argument "this" tells the system that this
        //       object would like to be the owner of the clipboard.
        //       As such, this object must implement the ClipboardOwner interface
        System.out.println("COPY: set system clipboard to Transferable");
        cb.setContents(transferObject, owner);
    }

    public String doPaste() {

       String text = null;

        // Grab system clipboard
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        System.out.println(String.format("PASTE: %d available flavours ... ",
            systemClipboard.getAvailableDataFlavors().length));
        for (DataFlavor f: systemClipboard.getAvailableDataFlavors()) {
            System.out.println("  " + f.getHumanPresentableName() + "  " + f.toString());
        }

        // Check if we can get the data as a string
        if (systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            System.out.println("DataFlavor.stringFlavor available");
            try {
                // Grab the data, set our text area to the data
                text = (String)systemClipboard.getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("DataFlavor.stringFlavor NOT available");
        }
        return text;
    }

}
