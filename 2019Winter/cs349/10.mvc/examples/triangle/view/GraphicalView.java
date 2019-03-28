package view;

import model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

/**
 * A view of a right triangle that displays the triangle graphically and allows
 * the user to change the size by dragging the image with a mouse.
 */
public class GraphicalView extends JComponent {
	private TriangleModel model;

	private double scale = 1.0; // how much should the triangle be scaled?

	private int handleSize = 5; // size of selectable square for dragging

	// To format numbers consistently in the text fields.
	private static final NumberFormat formatter = NumberFormat
			.getNumberInstance();

	public GraphicalView(TriangleModel aModel) {
		super();
		this.model = aModel;
		this.layoutView();
		this.registerControllers();
		this.model.addView(new IView() {
			/** The model changed. Ask the system to repaint the triangle. */
			public void updateView() {
				repaint();
			}
		});
	}

	/** How should it look on the screen? */
	private void layoutView() {
		this.setPreferredSize(new Dimension(200, 200));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	/** Register event Controllers for mouse clicks and motion. */
	private void registerControllers() {
		MouseInputListener mil = new MController();
		this.addMouseListener(mil);
		this.addMouseMotionListener(mil);
	}

	/** Paint the triangle with "handle" for resizing */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Insets insets = this.getInsets();

		this.scale = Math.min((this.getWidth() - insets.left - insets.right)
				/ (TriangleModel.MAX_SIDE + 2),
				(this.getHeight() - insets.top - insets.bottom)
						/ (TriangleModel.MAX_SIDE + 2));

		double base = this.model.getBase();
		double height = this.model.getHeight();
		double hypo = this.model.getHypotenuse();

		int left = this.toX(0);
		int right = this.toX(base);
		int bottom = this.toY(0);
		int top = this.toY(height);

		// Draw the triangle
		g.setColor(Color.black);
		g.drawLine(left, bottom, right, bottom);
		g.drawLine(right, bottom, right, top);
		g.drawLine(left, bottom, right, top);

		// Label the edges
		g.drawString(formatter.format(base), left + (right - left) / 2, bottom);
		g.drawString(formatter.format(height), right, top + (bottom - top) / 2);
		g.drawString(formatter.format(hypo), left + (right - left) / 2 - 15,
				top + (bottom - top) / 2 - 15);

		/** Draw "handles" for resizing the triangle. */
		// g.fillRect(right - handleSize, bottom - handleSize, 
		// 	handleSize * 2, handleSize * 2);
		g.fillRect(right - handleSize, top - handleSize, 
			handleSize * 2, handleSize * 2);

	}

	/** Convert from the model's X coordinate to the component's X coordinate. */
	protected int toX(double modelX) {
		return (int) (modelX * this.scale) + this.getInsets().left;
	}

	/** Convert from the model's Y coordinate to the component's Y coordinate. */
	protected int toY(double modelY) {
		return this.getHeight() - (int) (modelY * this.scale) - 1
				- this.getInsets().bottom;
	}

	/** Convert from the component's X coordinate to the model's X coordinate. */
	protected double fromX(int x) {
		return (x - this.getInsets().left) / this.scale;
	}

	/** Convert from the component's Y coordinate to the model's Y coordinate. */
	protected double fromY(int y) {
		return (this.getHeight() - 1 - this.getInsets().bottom - y)
				/ this.scale;
	}

	/**
	 * Does the given point (screen coordinates) lie on the upper right corner
	 * of the triangle?
	 */
	private boolean onTopCorner(int x, int y) {
		return Math.abs(fromX(x) - this.model.getBase()) < handleSize
				&& Math.abs(fromY(y) - this.model.getHeight()) < handleSize;
	}

	private class MController extends MouseInputAdapter {

		private boolean selected = false; // did the user select the triangle to
		// resize it?

		public void mousePressed(MouseEvent e) {
			selected = onTopCorner(e.getX(), e.getY());
		}

		// public void mouseReleased(MouseEvent e) {
		// 	selected = onTopCorner(e.getX(), e.getY());
		// }

		/** The user is dragging the mouse. Resize appropriately. */
		public void mouseDragged(MouseEvent e) {
			if (selected) {
				model.setBase(fromX(e.getX()));
				model.setHeight(fromY(e.getY()));
			}
		} 
	} // MController
} // GraphicalView

