// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/
// (C) Joseph Mack 2011, jmack (at) wm7d (dot) net, released under GPL v3 (or any later version)

import java.awt.event.*;

class Controller implements ActionListener {

	Model model;

	Controller(Model model) {
		this.model = model;
	}

	// event from the view's button
	public void actionPerformed(java.awt.event.ActionEvent e){
		System.out.println("Controller: changing Model");
		model.incrementCounter();
	}
}
