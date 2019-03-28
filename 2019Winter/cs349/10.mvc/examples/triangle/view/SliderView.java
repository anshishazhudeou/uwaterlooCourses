package view;

import model.*;

import javax.swing.*;
import javax.swing.event.*;

public class SliderView extends JPanel {
	private TriangleModel model;
	private JSlider baseSlider = new JSlider(0, (int) TriangleModel.MAX_HYPO);
	private JSlider heightSlider = new JSlider(0, (int) TriangleModel.MAX_HYPO);
	private JSlider hypoSlider = new JSlider(0, (int) TriangleModel.MAX_HYPO);

	public SliderView(TriangleModel aModel) {
		super();
		this.model = aModel;
		this.layoutView();
		this.registerControllers();
		this.model.addView(new IView() {
			public void updateView() {
				baseSlider.setValue((int) model.getBase());
				heightSlider.setValue((int) model.getHeight());
				hypoSlider.setValue((int) model.getHypotenuse());
			}
		});
	}

	private void layoutView() {
		this.setLayout(new FormLayout());
		this.add(new JLabel("Base:"));
		this.add(this.baseSlider);
		this.add(new JLabel("Height:"));
		this.add(this.heightSlider);
		this.add(new JLabel("Hypotenuse:"));
		this.add(this.hypoSlider);

		this.baseSlider.setMajorTickSpacing((int) TriangleModel.MAX_HYPO / 10);
		this.baseSlider.setPaintTicks(true);

		this.heightSlider
				.setMajorTickSpacing((int) TriangleModel.MAX_HYPO / 10);
		this.heightSlider.setPaintTicks(true);

		this.hypoSlider.setMajorTickSpacing((int) TriangleModel.MAX_HYPO / 10);
		this.hypoSlider.setPaintTicks(true);

		this.hypoSlider.setEnabled(false);
	}

	private void registerControllers() {
		this.baseSlider.addChangeListener(new BaseController());
		this.heightSlider.addChangeListener(new HeightController());
	}

	private class BaseController implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			double base = baseSlider.getValue();
			model.setBase(base);
		}
	}

	private class HeightController implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			double height = heightSlider.getValue();
			model.setHeight(height);
		}
	}
}
