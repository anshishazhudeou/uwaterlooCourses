// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

class View extends JPanel  {

	// the view's main user interface
	private JButton button;

	// the model that this view is showing
	private Model model;


	public View(Model model) {
		
		// create the view UI
		button = new JButton("?");
		button.setMaximumSize(new Dimension(100, 50));
		button.setPreferredSize(new Dimension(100, 50));
		// a GridBagLayout with default constraints centres
		// the widget in the window
		this.setLayout(new GridBagLayout());
		this.add(button, new GridBagConstraints());

		// set the model
		this.model = model;

		// anonymous class acts as model listener
		this.model.addView(new IView() {
			public void updateView() {
				System.out.println("View: updateView");
				button.setText(Integer.toString(model.getCounterValue()));
			}
		});


		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.incrementCounter();
			}
		});
	}


}
