import javax.swing.JFrame;

import model.TriangleModel;
import view.*;
import java.awt.GridLayout;

public class Main3 {

	public static void main(String[] args) {
		model.TriangleModel model = new TriangleModel();
		TextView vText = new TextView(model);
		ButtonView vButton = new ButtonView(model);
		SliderView vSlider = new SliderView(model);
		SpinnerView vSpinner = new SpinnerView(model);

		JFrame frame = new JFrame("Triangle Main3");
		frame.getContentPane().setLayout(new GridLayout(2, 2));
		frame.getContentPane().add(vText);
		frame.getContentPane().add(vButton);
		frame.getContentPane().add(vSlider);
		frame.getContentPane().add(vSpinner);

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
