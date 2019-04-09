// refer some code from mvc and lec 9

package main.java;
import javax.swing.*;
import java.awt.*;

import main.java.Model;

import java.awt.event.*;


class PalettesCanvasView extends JPanel implements IView {

    private Model model;

    int canvasPanelLength = 1600;
    int getCanvasPanelHeight = 1200;


    // declare initialX and initial Y whcih will to used for two classes later on
    int initialX;
    int initialY;

    public void draw(Graphics2D g2) {
        int x = this.getWidth();
        int y = this.getHeight();
        float scale = 1.0f;
        g2.scale(scale,scale);

    }



    private	JPanel canvasCustimized = new JPanel() {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            model.drawPanel(g2);
        }
    };

    // ctor
    PalettesCanvasView(Model model) {
        this.model=model;

        // Create the view UI
        // set the layout for the canvasPanel
        JPanel canvasPanel = new JPanel(new BorderLayout());
        canvasPanel.setMaximumSize(new Dimension(canvasPanelLength, getCanvasPanelHeight));
        canvasPanel.setPreferredSize(new Dimension(canvasPanelLength, getCanvasPanelHeight));



        // ccreate a newJPanel to fit the white area
        JPanel canvasWhite = new JPanel();

        canvasWhite.setBackground(Color.WHITE);
        canvasWhite.setPreferredSize(new Dimension(canvasPanelLength, getCanvasPanelHeight));

        JPanel canvasCenter = new JPanel((new FlowLayout((FlowLayout.CENTER))));

        canvasCenter.add(canvasWhite);
        this.add(canvasCenter);

        // add mouse motion listener to track down the movement of the mouse

        canvasWhite.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getX();
                int y = e.getY();
                if(model.currTool == toolButtonList.LINE || model.currTool == toolButtonList.CIRCLE || model.currTool == toolButtonList.RECTANGLE){
                    model.drawGraph(initialX, initialY, x, y);
                } else if (model.currTool == toolButtonList.SELECT) {
                    model.moveSelectedGraph(x,y);
                }
            }
        });

        canvasWhite.addMouseListener((new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int initialX =e.getX();
                int initialY = e.getY();

                if (model.currTool == toolButtonList.SELECT) {
                    model.toolSelect(initialX,initialY);
                } else if (model.currTool == toolButtonList.ERASE){
                    model.toolErase(initialX,initialY);
                } else if (model.currTool == toolButtonList.FILL) {
                    model.toolFill(initialX,initialY);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(model.currTool == toolButtonList.LINE || model.currTool == toolButtonList.CIRCLE || model.currTool == toolButtonList.RECTANGLE){
                    // we need to track down the dimension of curr graph, since we might need to repaint them later on
                    // add currGraph to the listofGraphs
                    model.addGraph();
                }
            }
        }));



    }
    // IView interface
    public void updateView() {
        //canvasFull.repaint();
        canvasCustimized.repaint();
    }



}
