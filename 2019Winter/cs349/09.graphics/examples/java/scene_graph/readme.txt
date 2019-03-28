This is a sample of drawing and manipulating a scene graph in Java. It includes the following files:

Makefile:               'make run' builds and executes the sample             
Main.java:              populates a Canvas with Sprites, displays in a JFrame.            
Canvas.java:            a drawing surface to hold all of the sprites   
Sprite.java:            an abstract drawable (sprite) that can be extended.
RectangleSprite.java:   an example of a specific shape that extends Sprite.

You can click-drag to move a shape and its children will follow. However, the code is incomplete and the last shape doesn't track properly (this obviously needs to be fixed). Also, rotation and scaling are stubbed out but not implemented.