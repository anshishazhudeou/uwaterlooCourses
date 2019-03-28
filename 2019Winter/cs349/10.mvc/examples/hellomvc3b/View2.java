// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;

class View2 extends JPanel {

	// the model that this view is showing
	private Model model;
	private JLabel label = new JLabel();

	View2(Model model) {
		// create UI
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		// set the model
		this.model = model;

		// anonymous class acts as model listener
		this.model.addView(new IView() {
			public void updateView() {
				System.out.println("View2: updateView");
				// just displays an 'X' for each counter value
				String s = "";
				for (int i=0; i< model.getCounterValue(); i++) s = s + "X";
				label.setText(s);
			}
		});		
			
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)		
		addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					model.incrementCounter();
				}
		});

		this.add(this.label);
	}

}
