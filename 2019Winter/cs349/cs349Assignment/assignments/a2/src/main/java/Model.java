// refer some code from mvc
// this class is used for implement mvc pattern

package main.java;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

enum toolButtonList {
    SELECT, ERASE, LINE, CIRCLE, RECTANGLE, FILL
}
/*
// methods need to be done
1. public graph
methods need to be implemented
1. drawPanel(x,y)  done
2. drawgraph done
3. moveSelectedGraph(x,y) done
4. toolSelect(initialX,initialY) // + difference
5  toolErase(initialX, initialY) // toolErase
6. toolFill(initialX, initialY)
*/
public class Model {

    toolButtonList currTool = null;
    Color currDrawColour = Color.BLACK;
    Color currFillingColour = Color.WHITE;
    int thickness = 1;
    Graphic currGraph = null;
    Graphic selectedGraph = null;

    // all views of this model
    private ArrayList<IView> views = new ArrayList<IView>();

    // main a list that contains all the grapch that we need to draw
    ArrayList<Graphic> listOfGraphs = new ArrayList<Graphic>();

    public ArrayList<Graphic> getListOfGraphs() {
        return listOfGraphs;
    }
    // set the view observer

    public void addView(IView view) {
        views.add(view);
        // update the view to current state of the model
        view.updateView();
    }


    // for tool
    public void switchCurrentTool(toolButtonList nameOfTool){
        currTool = nameOfTool;
        notifyObservers();
    }

    // for colour
    // switchCurrentColour
    public void switchCurrentColour(Color colour){
        currDrawColour = colour;
    }

    // for thickness line

    public void switchCurrThickness(int number) {
        thickness = number;
    }



    // notify the IView observer
    public void notifyObservers() {
        for (IView view : this.views) {
            view.updateView();
        }

    }


    public void drawPanel(Graphics2D g2) {

        // draw what we are drawing first
        if (this.currGraph.graphShape != null) {
            // for the line thickness and colour
            g2.setStroke(new BasicStroke(thickness));
            g2.setColor(currDrawColour);
            g2.draw(currGraph.graphShape);
        }
        // Drate all graphs in listOfGraps that we stored before which are all on the concavas
        for (Graphic  graph : listOfGraphs){
            g2.setStroke(new BasicStroke(graph.lineThickness));
            g2.setColor(graph.drawColour);
            g2.draw(graph.graphShape);

            // check if the current graph needs to be filled
            if (graph.fillingColour != null) {
                g2.setColor(graph.fillingColour);
                g2.fill(graph.graphShape);
            }
        }
        notifyObservers();

    }
    public void drawGraph(int initialX, int initialY, int x, int y){
        int xh = Math.min(initialX, x);
        int yv = Math.min(initialY,y);
        int length = Math.abs(initialX - x);
        int height = Math.abs(initialY - y);
        int radius = Math.max(length, height);
        if (currTool == toolButtonList.LINE) {

            currGraph = new Graphic(currDrawColour, currFillingColour, new Line2D.Float(initialX,initialY,x,y), thickness);
        } else if (currTool == toolButtonList.CIRCLE) {
            Shape temShape=new Ellipse2D.Float(x,y,radius,radius);

            currGraph = new Graphic(currDrawColour, currFillingColour, new Ellipse2D.Float(x,y,radius,radius), thickness);
        } else if (currTool == toolButtonList.RECTANGLE){
            currGraph = new Graphic(currDrawColour, currFillingColour, new Rectangle2D.Float(x,y,length,height), thickness);
        }
        notifyObservers();

    }
    public  void moveSelectedGraph(int x, int y) {
        if (selectedGraph != null) {
            double xOld=selectedGraph.graphShape.getBounds().getX();
            double yOld=selectedGraph.graphShape.getBounds().getY();

            AffineTransform ts = new AffineTransform();
            ts.translate(x-xOld, y-yOld);
            selectedGraph.graphShape = ts.createTransformedShape(selectedGraph.graphShape);
        }
        notifyObservers();

    }

    public void toolSelect(int initialX,int initialY) {
        for (Graphic graph : listOfGraphs) {
            if (graph.graphShape.contains(initialX,initialY)) {
                selectedGraph = graph;
                thickness = selectedGraph.lineThickness;
                currDrawColour = selectedGraph.drawColour;
                break;
            }
        }
        notifyObservers();
    }
    public void toolErase(int initialX, int initialY) {
        for (Graphic graph : listOfGraphs) {
            boolean isContained=false;
            isContained =graph.graphShape.contains(initialX, initialY);
            if (isContained) {
                listOfGraphs.remove(graph);
                break;
            }
        }
        notifyObservers();

    }
    public void toolFill(int initialX, int initialY) {
        for(Graphic graph : listOfGraphs) {
            if(graph.graphShape.contains(initialX,initialY)){
                graph.fillingColour = currDrawColour;
            }
        }
        notifyObservers();
    }

    public void addGraph() {
        listOfGraphs.add(currGraph);
        notifyObservers();
    }

    public void newDrawing() {
        toolButtonList currTool =null;
        Color currDrawColour = null;
        Color currFillingColour=null;
        int thickness = 1;
        Graphic currGraph= null;
        Graphic selectedGraph = null;
        listOfGraphs = new ArrayList<Graphic>();
        notifyObservers();

    }



}