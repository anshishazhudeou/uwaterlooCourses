import javax.swing.JFrame;

import model.TriangleModel;
import view.*;
import java.awt.GridLayout;

public class Main4 {

	public static void main(String[] args) {
		model.TriangleModel model = new TriangleModel();
		GraphicalView vGraphical = new GraphicalView(model);

		JFrame frame = new JFrame("Triangle Main4");
		frame.getContentPane().add(vGraphical);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
