# Widgets and Event Handling (Java) -- JSketch



# Features!
The main area of your drawing program is a canvas that supports drawing shapes. Users can select a shape from the list on the side, specify color and border thickness, then click and drag the mouse on the canvas to draw that shape. A shape "preview" is shown as the mouse is dragged, and the shape is complete when the mouse button is released (i.e. the first click sets the starting position, and the drag operation sets the width of the circle, or creates a line, depending on the shape being drawn, and the shape is complete when the mouse button is released)

  - A menu bar with a File menu containing the following items
    --New: create a new drawing. The drawing will always fit the canvas when it is created.
    --Load: load a drawing from a file (i.e. a file that you previously saved, which      replaces the current drawing).
    --Save: save the current drawing (using a file format that you determine).
   - A tool palette on the left-hand side, supporting the following tools:
     --A selection tool
      --An erase tool
       --A line drawing tool
     --A circle drawing tool
    --A rectangle tool
   --A fill tool
   - color palette
   -  line thickness palette