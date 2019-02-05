// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

/**
 * One view.  Separate controller.
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;

public class Main {

	public static void main(String[] args){
		JFrame frame = new JFrame("HelloMVC1");

		// create Model and initialize it
		Model model = new Model();
		// create Controller, tell it about model
		Controller controller = new Controller(model);
		// create View, tell it about model and controller
		View view = new View(model, controller);
		// tell Model about View.
		model.setView(view);

		// add view (view is a JPanel)
		frame.getContentPane().add(view);

		// setup window
		frame.setPreferredSize(new Dimension(300,300));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
