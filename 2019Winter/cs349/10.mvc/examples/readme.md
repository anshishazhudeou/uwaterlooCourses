# MVC Code Demos

## Hello MVC Demos

Simple MVC examples using an incremental counter model. Each directory has a make file with MVC classes. These demos were inspired by Joseph Mack: http://www.austintek.com/mvc/ 

* `nomvc/` same functionality as MVC demos, but not using MVC
* `hellomvc1/` separate view and controller classes, only 1 view
* `hellomvc2/` separate view and controller classes, multiple views
* `hellomvc3/` controller combined into view, multiple views
* `hellomvc3b/` controller combined into view, views use modelListeners
* `hellomvc4/` using Java's Observer interface and Observable base class

## Triangle Demos

These are more complex MVC examples using a right-angle triangle model

All use the same "right-angle triangle" Model:

* `model/TriangleModel.java` the model used in every example
* `model/IView.java` the view interface used in every example

There are different Views and Controllers:

* `SimpleTextView.java` a very limited but simple view 
* `TextView.java` a text view with correct controller updates and protected field
* `SliderView` uses slider widgets to adjust side lengths
* `SpinnerView` uses spinner widgets to adjust side lengths 
* `GraphicalView` adjust side length using direct manipulation of a picture of the triangle

These views are combined in different ways. Set makefile NAME to one of the following startup classes to run:

* `Main1.java`			SimpleTextView only
* `Main2.java`			TextView only
* `Main3.java`			multiple views at once
* `Main4.java`			GraphicalView demo
* `Main5.java` 			multiple views using a tab

These demos also show how to use packages to organize code and they use the `FormLayout` custom layout manager.