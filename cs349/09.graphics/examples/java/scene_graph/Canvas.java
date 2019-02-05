
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A canvas that draws sprites.
 * 
 * Michael Terry & Jeff Avery
 */
public class Canvas extends JPanel {

	private Vector<Sprite> sprites = new Vector<Sprite>(); // All sprites we're managing
	private Sprite interactiveSprite = null; // Sprite with which user is interacting

	public Canvas() {
		// Install our event handlers
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				handleMousePress(e);
			}

			public void mouseReleased(MouseEvent e) {
				handleMouseReleased(e);
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				handleMouseDragged(e);
			}
		});
	}

	/**
	 * Handle mouse press events
	 */
	private void handleMousePress(java.awt.event.MouseEvent e) {
		for (Sprite sprite : sprites) {
			interactiveSprite = sprite.getSpriteHit(e);
			if (interactiveSprite != null) {
				interactiveSprite.handleMouseDownEvent(e);
				break;
			} 
		}
	}

	/**
	 * Handle mouse released events
	 */
	private void handleMouseReleased(MouseEvent e) {
		if (interactiveSprite != null) {
			interactiveSprite.handleMouseUp(e);
			repaint();
		}
		interactiveSprite = null;
	}

	/**
	 * Handle mouse dragged events
	 */
	private void handleMouseDragged(MouseEvent e) {
		if (interactiveSprite != null) {
			interactiveSprite.handleMouseDragEvent(e);
			repaint();
		}
	}

	/**
	 * Add a top-level sprite to the canvas
	 */
	public void addSprite(Sprite s) {
		sprites.add(s);
	}

	/**
	 * Paint our canvas
	 */
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		for (Sprite sprite : sprites) {
			sprite.draw((Graphics2D) g);
		}
	}
}
