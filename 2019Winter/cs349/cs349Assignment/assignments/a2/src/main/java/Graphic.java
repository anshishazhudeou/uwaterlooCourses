package main.java;
import java.awt.*;
import java.io.Serializable;
public class Graphic implements Serializable{
    Color drawColour =null;
    Color fillingColour=null;
    Shape graphShape=null;
    Integer lineThickness=0;

    Graphic(Color drawColour, Color fillingColour, Shape graphShape, Integer lineThickness) {
        this.drawColour = drawColour;
        this.fillingColour = fillingColour;
        this.graphShape = graphShape;
        this.lineThickness = lineThickness;
    }

}
