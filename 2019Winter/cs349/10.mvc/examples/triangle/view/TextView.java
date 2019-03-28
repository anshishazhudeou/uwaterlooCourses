package view;

import model.IView;
import model.TriangleModel;

import javax.swing.*;
import java.awt.event.*;
import java.text.NumberFormat;

/*
 * View a triangle as text:  numbers for the base, height, and hypotenuse.
 * 
 * @author Byron Weber Becker
 */
public class TextView extends JPanel {

	private TriangleModel model;

	private JTextField baseTF = new JTextField(10);
	private JTextField heightTF = new JTextField(10);
	private JTextField hypoTF = new JTextField(10);

	// To format numbers consistently in the text fields.
	private static final NumberFormat formatter = NumberFormat
			.getNumberInstance();

	public TextView(TriangleModel aModel) {
		super();
		this.model = aModel;
		this.layoutView();
		this.registerControllers();

		// There's no need to do this for such a simple view, but for a complex
		// view you might have several ViewUpdate objects registered with
		// the model.
		this.model.addView(new IView() {
			public void updateView() {
				baseTF.setText(formatter.format(model.getBase()));
				heightTF.setText(formatter.format(model.getHeight()));
			}
		});

		this.model.addView(new IView() {
			public void updateView() {
				hypoTF.setText(formatter.format(model.getHypotenuse()));
			}
		});
	}

	private void layoutView() {
		this.setLayout(new FormLayout());
		this.add(new JLabel("Base:"));
		this.add(this.baseTF);
		this.add(new JLabel("Height:"));
		this.add(this.heightTF);
		this.add(new JLabel("Hypotenuse:"));
		this.add(this.hypoTF);

		// Don't allow the user to edit the hypotenuse
		this.hypoTF.setEditable(false); // can still select and copy
		//this.hypoTF.setEnabled(false); // greyed out, can't select
	}

	private void registerControllers() {
		this.baseTF.addActionListener(new BaseController());
		this.baseTF.addFocusListener(new BaseFocusController());

		CombinedHeightController hc = new CombinedHeightController();
		this.heightTF.addActionListener(hc);
		this.heightTF.addFocusListener(hc);
	}

	private class BaseController implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			double base = Double.parseDouble(baseTF.getText());
			model.setBase(base);
		}
	}

	private class BaseFocusController implements FocusListener {
		public void focusGained(FocusEvent evt) {
			//baseTF.selectAll();
		}

		public void focusLost(FocusEvent evt) {
			// Note duplicated code.
			double value = Double.parseDouble(baseTF.getText());
			model.setBase(value);
		}
	}

	private class CombinedHeightController implements ActionListener,
			FocusListener {
		private void setHeight() {
			double height = Double.parseDouble(heightTF.getText());
			model.setHeight(height);
		}

		public void actionPerformed(ActionEvent evt) {
			this.setHeight();
		}

		public void focusGained(FocusEvent evt) {
			//heightTF.selectAll();
		}

		public void focusLost(FocusEvent evt) {
			this.setHeight();
			baseTF.requestFocus(); // skip over hypotenuse
		}
	}
}