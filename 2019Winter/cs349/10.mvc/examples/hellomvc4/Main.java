// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

/**
 *  Two views with integrated controllers.  Uses java.util.Observ{er, able} instead
 *  of custom IView.
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;	

public class Main {

	public static void main(String[] args){	
		JFrame frame = new JFrame("HelloMVC4");
		
		// create Model and initialize it
		Model model = new Model();
		
		// create View, tell it about model (and controller)
		View view = new View(model);
		// tell Model about View. 
		model.addObserver(view);
		
		// create second view ...
		View2 view2 = new View2(model);
		model.addObserver(view2);
		
		// let all the views know that they're connected to the model
		model.notifyObservers();
		
		// create a layout panel to hold the two views
		JPanel p = new JPanel(new GridLayout(2,1));
		frame.getContentPane().add(p);

		// add views (each view is a JPanel)
		p.add(view);
		p.add(view2);
		
		// setup window
		frame.setPreferredSize(new Dimension(300,300));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} 
}
