package view;

import model.IView;
import model.TriangleModel;

import javax.swing.*;
import java.awt.event.*;
import java.text.NumberFormat;

/*
 * View a triangle as text:  numbers for the base, height, and hypotenuse.
 * This view leaves a lot to be desired in terms of "polish".
 * -- inconsistent decimal precision displayed, esp. in hypotenuse
 * -- tabbing or clicking out of a text field doesn't update
 * -- can edit the hypotenuse field
 * 
 * @author Byron Weber Becker
 */
public class SimpleTextView extends JPanel implements IView {

	private TriangleModel model;
	
	private JTextField baseTF = new JTextField(10);
	private JTextField heightTF = new JTextField(10);
	private JTextField hypoTF = new JTextField(10);

	public SimpleTextView(TriangleModel aModel) {
		super();
		this.model = aModel;
		this.layoutView();
		this.registerControllers();

		// Add a this view as a listener to the model
		this.model.addView(this);
	}
	
	/**
	 * What to do when the model changes.
	 */
	public void updateView() {
		baseTF.setText("" + model.getBase());
		heightTF.setText("" + model.getHeight());
		hypoTF.setText("" + model.getHypotenuse());
	}

	private void layoutView() {
		this.setLayout(new FormLayout());
		this.add(new JLabel("Base:"));
		this.add(this.baseTF);
		this.add(new JLabel("Height:"));
		this.add(this.heightTF);
		this.add(new JLabel("Hypotenuse:"));
		this.add(this.hypoTF);
	}

	private void registerControllers() {
		// Add a controller to interpret user actions in the base text field
		this.baseTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				double base = Double.parseDouble(baseTF.getText());
				model.setBase(base);
			}
		});

		// Add a controller to interpret user actions in the height text field
		this.heightTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				double height = Double.parseDouble(heightTF.getText());
				model.setHeight(height);
			}
		});
	}
}