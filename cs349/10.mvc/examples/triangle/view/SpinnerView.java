package view;

import java.text.NumberFormat;

import model.*;

import javax.swing.*;
import javax.swing.event.*;

public class SpinnerView extends JPanel {
	private TriangleModel model;

	// The spinner requires a number model that specifies the starting
	// value, the minimum, maximum, and step size.
	private JSpinner base = new JSpinner(new SpinnerNumberModel(1, 1,
			TriangleModel.MAX_SIDE, 1));
	private JSpinner height = new JSpinner(new SpinnerNumberModel(1, 1,
			TriangleModel.MAX_SIDE, 1));
	private JLabel hypo = new JLabel();
	private static final NumberFormat formatter = NumberFormat
			.getNumberInstance();

	public SpinnerView(TriangleModel aModel) {
		super();
		this.model = aModel;
		this.layoutView();
		this.registerControllers();

		this.model.addView(new IView() {
			public void updateView() {
				base.setValue(new Double(model.getBase()));
				height.setValue(new Double(model.getHeight()));
				hypo.setText(formatter.format(model.getHypotenuse()));
			}
		});

	}

	private void layoutView() {
		this.setLayout(new FormLayout());
		this.add(new JLabel("Base:"));
		this.add(this.base);
		this.add(new JLabel("Height:"));
		this.add(this.height);
		this.add(new JLabel("Hypotenuse:"));
		this.add(this.hypo);

	}

	private void registerControllers() {
		// Instantiate an anonymous ChangeListener class.
		// Then add it to both spinners. Having one class
		// that handles both spinners is one style; I prefer
		// having separate listeners, one for each widget.
		ChangeListener sc = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSpinner src = (JSpinner) e.getSource();
				double val = ((Double) src.getValue()).doubleValue();
				if (src == SpinnerView.this.base) {
					SpinnerView.this.model.setBase(val);
				} else if (src == SpinnerView.this.height) {
					SpinnerView.this.model.setHeight(val);
				} else {
					assert false;
				}
			}
		};

		this.base.addChangeListener(sc);
		this.height.addChangeListener(sc);
	}
}