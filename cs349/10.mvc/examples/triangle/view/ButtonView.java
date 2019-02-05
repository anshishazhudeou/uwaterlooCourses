package view;

import model.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.NumberFormat;
import java.awt.GridLayout;

public class ButtonView extends JPanel {
	private TriangleModel model;
	private JLabel base = new JLabel("0.0");
	private JLabel height = new JLabel("0.0");
	private JLabel hypo = new JLabel("0.0");
	private JButton baseUp = new JButton("+");
	private JButton baseDn = new JButton("-");
	private JButton heightUp = new JButton("+");
	private JButton heightDn = new JButton("-");

	private NumberFormat formatter = NumberFormat.getNumberInstance();
	private static final int MAX_FRACTION_DIGITS = 5;

	public ButtonView(TriangleModel aModel) {
		super();
		this.model = aModel;

		this.layoutView();
		this.registerControllers();

		this.model.addView(new IView() {
			public void updateView() {
				base.setText(formatter.format(model.getBase()));
				height.setText(formatter.format(model.getHeight()));
				hypo.setText(formatter.format(model.getHypotenuse()));

				// Updating the view includes enabling/disabling components!
				baseUp.setEnabled(model.getBase() < TriangleModel.MAX_SIDE);
				baseDn.setEnabled(model.getBase() > 1);
				heightUp.setEnabled(model.getHeight() < TriangleModel.MAX_SIDE);
				heightDn.setEnabled(model.getHeight() > 1);
			}

		});
	}

	private void layoutView() {
		this.setLayout(new FormLayout());
		this.add(new JLabel("Base:"));
		this.add(this.groupComponents(this.baseUp, this.baseDn, this.base));

		this.add(new JLabel("Height:"));
		this.add(this
				.groupComponents(this.heightUp, this.heightDn, this.height));

		this.add(new JLabel("Hypotenuse:"));
		this.add(this.hypo);

		Dimension d = this.hypo.getPreferredSize();
		d.width = 80;
		this.base.setPreferredSize(d);
		this.height.setPreferredSize(d);
		this.hypo.setPreferredSize(d);

		this.formatter.setMaximumFractionDigits(MAX_FRACTION_DIGITS);
	}

	private Box groupComponents(JButton up, JButton dn, JLabel label) {
		Box group = Box.createHorizontalBox();
		group.add(up);
		group.add(dn);
		group.add(label);

		Dimension d = up.getPreferredSize();
		d.width = Math.max(up.getPreferredSize().width,
				dn.getPreferredSize().width);
		up.setPreferredSize(d);
		dn.setPreferredSize(d);

		return group;
	}

	private void registerControllers() {
		this.baseUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setBase(model.getBase() + 1);
			}
		});

		this.baseDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setBase(model.getBase() - 1);
			}
		});

		this.heightUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setHeight(model.getHeight() + 1);
			}
		});

		this.heightDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setHeight(model.getHeight() - 1);
			}
		});
	}

}
