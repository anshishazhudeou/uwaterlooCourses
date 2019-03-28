// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;

class View2 extends JPanel implements IView {

	// the model that this view is showing
	private Model model;
	private JLabel label = new JLabel();

	View2(Model model, Controller controller) {

		// create UI
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(this.label);

		// set the model
		this.model = model;
		
		// setup the event to go to the controller
		addMouseListener(controller);
	}

	// IView interface
	public void updateView() {
		System.out.println("View2: updateView");
		// just displays an 'X' for each counter value
		String s = "";
		for (int i=0; i<this.model.getCounterValue(); i++) s = s + "X";
		this.label.setText(s);
	}
}
