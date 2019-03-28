// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

/**
 * Views using a listener anonymous class to handle Model update "events".
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;	

public class Main {

	public static void main(String[] args){	
		JFrame frame = new JFrame("HelloMVC3b");
		
		// create Model and initialize it
		Model model = new Model();
		
		// create View, tell it about model
		View view = new View(model);
	
		// create second view ...
		View2 view2 = new View2(model);
		
		// create a layout panel to hold the two views
		JPanel p = new JPanel(new GridLayout(2,1));
		frame.getContentPane().add(p);

		// add views (each view is a JPanel)
		p.add(view);
		p.add(view2);
		
		// create the window
		frame.setPreferredSize(new Dimension(300,300));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} 
}
