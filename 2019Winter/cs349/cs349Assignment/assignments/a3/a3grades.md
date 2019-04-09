# CS349 A3
Student: l62zhou
Marker: Alexandra Vtyurina


Total: 75.0 / 100.0 (75.00%)

Code:
(CO: won’t compile, CR: crashes, FR: UI freezes/unresponsive, NS: not submitted)


Notes:  MarkingMenuDemo is not in a separate file. Events are not propagated through to the View class. 

## TECHNICAL REQUIREMENTS (10)

1. [5/5] Gradle.build exists, `gradle build` and `gradle run` works successfully.

-5 There is no build config file; or it didn't work
-4 There was a problem in the gradle.build, which the TA had to fix to run the program; details:
-3 The build file works to compile the program, but not to run it

2. [5/5] README file exists and some effort was made to document functionality and/or design.

-5 Missing or empty readme file

3. [0/0] The starter code was used and modified to reflect the functionality of the marking menu widget.

-25 Assignment doesn't use the starting code

4. [0/0] Assignment uses Java/Swing.

-50 Assignment was written in some other programming language and/or toolkit (penalty may be waived at instructor's discretion for a particularly clever implementation, that is significantly more difficult to implement than the assignment would have been).

## MODEL-VIEW-CONTROLLER IMPLEMENTATION (20)

1. [5/5] A Main class exists, which is used by gradle to launch the program (it does not need to be named `Main` but should be a specific class, whose purposes is to launch the application).

2. [10/10] Application has been refactored to include at least the following classes: Model, View, MarkingMenu, and Main (note that classes do not have to use these explicit names, but should have names that reflect their purpose).

-5 One of these classes is missing (up to -10 for more than one class missing)
-5 Name of the class doesn't reflect it's purpose (penalty applied at TA discretion for a very confusing structure)

3. [0/5] The Model class contains both the text representation, and the properties for the text (e.g. foreground and background colours).

-5 Model does not contain the text string.
-5 TextArea is referenced directly in the model.
-5 No properties are included in the model.

## FUNCTIONAL REQUIREMENTS (40)

1. [5/5] In the application, a user can left or right click to invoke the marking menu.

-5 Neither left nor right click invokes the marking menu.

2. [10/10] Your marking menu should handle 1 or 2 levels, with 1 to 8 items per level.

-5 Your marking menu only supports a single level (i.e. no L2 expansion).
-5 Your marking menu does not / cannot show the full range of items.

3. [15/15] Your marking menu should appear as a set of overlapping, circular panels. The menu should appear roughly centred over the mouse cursor at the time of activation. (Note: there is no penalty for a lack of transparency in the background, but it needs to be consistent - either transparent or opaque, with no flickering).

-5 No submenu is shown.
-5 Text not rendered properly.
-10 Background flickers or fails to draw properly.

4. [10/10] Users can select an item from the menu by clicking, or dragging-and-releasing the mouse over it, which causes the appropriate functionality to activate. If you only support one of these mechanisms, it must be documented in the README.

-10 Unable to activate a JMenUItem.
-10 Cannot expand a JMenu to view JMenuItems that it contains.
-10 Functional, but uses some other mechanism to activate an item (e.g. press a number on the keyboard)
-5 Nothing in README.

## MARKING MENU WIDGET DESIGN (30)

1. [0/5] `Marking Menu` should be self-contained, in a single file. You may use inner classes within your marking menu class if desired.

-5 The marking menu class is not a separate class/file, and has been defined within another class (e.g. inner class of the view).

2. [5/5] The marking menu should be a drop-in replacement for the `JPopupMenu` in the starter code. This implies that the View instantiates the `MarkingMenu` in the view's mouse listener (simplest implementation) and it's state is managed by the View class.

3. [10/10] Your class must support the methods that are required by the JPopupMenu in the sample application, including `add(JMenu)`, `add(JMenuItem)`. Users can add instances of the JMenu and JMenuItem classes to describe the layout. This implies that you must support the existing classes helper classes.

-10 One or more of the add methods doesn't work with the `JMenu` or `JMenuItem` classes.

4. [-5/10] Your marking menu class should allow other classes to register listeners that will receive events when the user interacts with the menu. This can be either an ActionEvent that your MarkingMenu publishes, or (more likely) that your implementation supports the existing publish mechanism, where the View can add listeners to each JMenuItem.

-10 Your class doesn't use listeners, but instead has some other way of notifying the view (e.g. it calls a public view method, or is implemented as a modal dialog that blocks input and returns a value on item selection).
-15 Your class doesn't notify the view of user selection.