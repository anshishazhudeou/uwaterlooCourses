// refer some code from 10.mvc
package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class PalettesToolView extends JPanel implements IView {


    // the view's main user interface
    private JButton button;

    // the model that this view is showing
    private Model model;
    String absolutePath = System.getProperty("user.dir") + "/src/main/java/";

    // for toolBarTool
    String[] toolLocation = new String[] {"palettesToolViewImages/tools/select.png", "palettesToolViewImages/tools/erase.png",
                        "palettesToolViewImages/tools/line.png", "palettesToolViewImages/tools/circle.png",
                     "palettesToolViewImages/tools/rectangle.png", "palettesToolViewImages/tools/fill.png"};


    // update the status for tools
    private JToggleButton switchToolButton(JToggleButton toolButton, toolButtonList nameOfToolButton) {


        // setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
        toolButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.switchCurrentTool(nameOfToolButton);
            }
        });
        return toolButton;

    }


    // for toolBarColour
    JToggleButton buttonBlue;
    JToggleButton buttonRed;
    JToggleButton buttonOrange;
    JToggleButton buttonYellow;
    JToggleButton buttonGreen;
    JToggleButton buttonPink;
    String[] imageLocation = new String[] {"palettesToolViewImages/colour/blue.png",
            "palettesToolViewImages/colour/red.png",
            "palettesToolViewImages/colour/orange.png",
            "palettesToolViewImages/colour/yellow.png",
            "palettesToolViewImages/colour/green.png",
            "palettesToolViewImages/colour/purple.png"};


    // update status for toolBar
    JToggleButton createColorButton(JToggleButton colorButton, Color coulour) {
        colorButton.setBackground(coulour);
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.switchCurrentColour(colorButton.getBackground());
            }
        });
        return colorButton;
    }


    // for chooser

    JToggleButton createChooserButton(JToggleButton chooserButton){
        //chooserButton.setMaximumSize(new Dimension(100,50));
        //chooserButton.setPreferredSize(new Dimension(100,50));
        chooserButton.setBackground(Color.GRAY);
        chooserButton.setAlignmentX(0.6F);

        chooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color chooseColor= JColorChooser.showDialog(null,"Choose a color", null);
                model.switchCurrentColour(chooseColor);
                model.switchCurrentColour(chooseColor);
            }
        });

        return chooserButton;
    }

    // for lineThickness
    String[] lineLocation = new String[] {"palettesToolViewImages/lineThickness/lineThickness0.png",
            "palettesToolViewImages/lineThickness/lineThickness1.png",
            "palettesToolViewImages/lineThickness/lineThickness2.png",
            "palettesToolViewImages/lineThickness/lineThickness3.png",

    };

    int [] lineThickness = new int[] {1, 2, 3, 4};
    JToggleButton line0;
    JToggleButton line1;
    JToggleButton line2;
    JToggleButton line3;

    JToggleButton createLineButton(JToggleButton thicknessButton, int thickness) {

        thicknessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                model.switchCurrThickness(thickness);
            }


        });
        return thicknessButton;

    }



    // for PalettesCanvasViews, we need to to 2 things
    // 1. set the layout
    // 2. add an actionListerner
    PalettesToolView(Model model) {
        // create the view UI
        this.model = model;


        // 1-1 set the layout for all tools
        // set the entire tool bar to be vertical
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // then we need to add three areas for each type of tool
        // add tool area
        JToolBar toolBarTools = new JToolBar(JToolBar.VERTICAL);
        // add colour area
        JToolBar toolBarColour = new JToolBar(JToolBar.VERTICAL);
        // add line thickness area
        JToolBar toolBarLineThinckness = new JToolBar(JToolBar.VERTICAL);

        // set the layout for the LHS toolBar
        JPanel toolBarLayout = new JPanel(new GridLayout(3, 2));

        // add each JToolBar to the boxLayout
        add(toolBarTools);
        add(toolBarColour);
        add(toolBarLineThinckness);



        // add each type of button to the toolBarLayout and create a correspoding listener to that button
        // tool goes first

        JToggleButton toolSelect= new JToggleButton(new ImageIcon(absolutePath+toolLocation[0]));
        switchToolButton(toolSelect,toolButtonList.SELECT);
        JToggleButton toolErase=new JToggleButton(new ImageIcon(absolutePath+toolLocation[1]));
        switchToolButton(toolErase,toolButtonList.ERASE);
        JToggleButton toolLine=new JToggleButton(new ImageIcon(absolutePath+toolLocation[2]));

        switchToolButton(toolLine,toolButtonList.LINE);
        JToggleButton toolCircle=new JToggleButton(new ImageIcon(absolutePath+toolLocation[3]));
        switchToolButton(toolCircle,toolButtonList.CIRCLE);
        JToggleButton toolRectangle=new JToggleButton(new ImageIcon(absolutePath+toolLocation[4]));
        switchToolButton(toolRectangle,toolButtonList.RECTANGLE);
        JToggleButton toolFill=new JToggleButton(new ImageIcon(absolutePath+toolLocation[5]));
        switchToolButton(toolFill,toolButtonList.FILL);


        // add the tool button by order
        toolBarLayout.add(toolSelect);
        toolBarLayout.add(toolErase);
        toolBarLayout.add(toolLine);
        toolBarLayout.add(toolCircle);
        toolBarLayout.add(toolRectangle);
        toolBarLayout.add(toolFill);


        // add toolBarTools to the toolBarTools
        toolBarTools.add(toolBarLayout);


        // similar to toolBarLayout above, we need to add
        JPanel toolBarColourLayout = new JPanel(new GridLayout(3,2));
        // add each type of colour button to the toolBarColour and create a corresponding listener to that button
        buttonBlue = createColorButton(new JToggleButton(new ImageIcon(absolutePath+imageLocation[0])),Color.BLUE);
        buttonRed = createColorButton(new JToggleButton(new ImageIcon(absolutePath+imageLocation[1])),Color.RED);
        buttonOrange = createColorButton(new JToggleButton(new ImageIcon(absolutePath+imageLocation[2])),Color.ORANGE);
        buttonYellow = createColorButton(new JToggleButton(new ImageIcon(absolutePath+imageLocation[3])),Color.YELLOW);
        buttonGreen = createColorButton(new JToggleButton(new ImageIcon(absolutePath+imageLocation[4])),Color.GREEN);
        buttonPink = createColorButton(new JToggleButton(new ImageIcon(absolutePath+imageLocation[5])),Color.PINK);

        toolBarColourLayout.add(buttonBlue);
        toolBarColourLayout.add(buttonRed);
        toolBarColourLayout.add(buttonOrange);
        toolBarColourLayout.add(buttonYellow);
        toolBarColourLayout.add(buttonGreen);
        toolBarColourLayout.add(buttonPink);

        toolBarColour.add(toolBarColourLayout);
        // done with colour part

        //start to deal with the chooser part
        JToggleButton chooserButton = new JToggleButton("Chooser");
        chooserButton = createChooserButton(chooserButton);
        toolBarColour.add(chooserButton);
        // done with chooser


        // for lineThickness
        JPanel toolBarLineLayout = new JPanel(new GridLayout(4,1));

        toolBarLineLayout.setPreferredSize(new Dimension(400,150));
        //toolBarLineLayout.setMaximumSize(new Dimension(200,200));
        // add each line with different thickness to toolBarLineThinckness
        JToggleButton line0 = new JToggleButton(new ImageIcon(absolutePath+lineLocation[0]));
        line0 = createLineButton(line0, 1);

        JToggleButton line1 = new JToggleButton(new ImageIcon(absolutePath+lineLocation[1]));
        line1 = createLineButton(line1, 2);

        JToggleButton line2 = new JToggleButton(new ImageIcon(absolutePath+lineLocation[2]));
        line2 = createLineButton(line2, 3);
        JToggleButton line3 = new JToggleButton(new ImageIcon(absolutePath+lineLocation[3]));
        line3 = createLineButton(line3, 4);


        /*line1 = createLineButton(new JToggleButton(new ImageIcon(absolutePath+lineLocation[1])), 2);
        line2 = createLineButton(new JToggleButton(new ImageIcon(absolutePath+lineLocation[2])), 3);
        line3 = createLineButton(new JToggleButton(new ImageIcon(absolutePath+lineLocation[3])), 4);*/

        toolBarLineLayout.add(line0);
        toolBarLineLayout.add(line1);
        toolBarLineLayout.add(line2);
        toolBarLineLayout.add(line3);

        toolBarLineThinckness.add(toolBarLineLayout);
    }



    // 2 update view
    // IView interface
    public void updateView() {
        System.out.println("View: PalettesToolViewUpdated");

    }
}
