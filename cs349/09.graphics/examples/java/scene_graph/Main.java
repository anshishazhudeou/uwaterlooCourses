
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {		

		// add scene graph to the canvas
		Canvas canvas = new Canvas();
		canvas.addSprite(Main.makeSprite());

		// create a frame to hold it
		JFrame f = new JFrame();
		f.getContentPane().add(canvas);
		f.getContentPane().setLayout(new GridLayout(1, 1));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 300);
		f.setVisible(true);
	}
	
	/* Make sample scene graph for testing purposes. */
	private static Sprite makeSprite() {
		// create four different parts
		Sprite firstSprite = new RectangleSprite(80, 50);
		// Sprite secondSprite = new RectangleSprite(50, 40);
		// Sprite thirdSprite = new RectangleSprite(70, 30);

		// // define them based on relative, successive transformations
		// firstSprite.transform(AffineTransform.getTranslateInstance(0, 25));
		// secondSprite.transform(AffineTransform.getTranslateInstance(80, 5));
		// thirdSprite.transform(AffineTransform.getTranslateInstance(50, 5));

		// // build scene graph
		// firstSprite.addChild(secondSprite);
		// secondSprite.addChild(thirdSprite);
		
		// return root of the tree
		return firstSprite;
	}

}
