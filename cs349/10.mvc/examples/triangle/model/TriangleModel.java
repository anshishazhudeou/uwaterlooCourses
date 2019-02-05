package model;

// Note!  Nothing from the view package is imported here.
import java.util.ArrayList;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoableEdit;

public class TriangleModel {

	/* A list of the model's views. */
	private ArrayList<IView> views = new ArrayList<IView>();

	// Limit the size of the triangle.
	public static final double MAX_SIDE = 100.0;
	public static final double MAX_HYPO = 
	    Math.sqrt(MAX_SIDE * MAX_SIDE + MAX_SIDE * MAX_SIDE);

	private double base = 50.0;  // length of the base
	private double height = 50.0;  // height of the triangle

	public TriangleModel() { }

	/** Set the base to a new value. Must be between 0 and MAX_BASE. */
	public void setBase(double theBase) {
		double tmp = Math.max(0, theBase);
		this.base = Math.min(tmp, MAX_SIDE);

		this.updateAllViews(); // update Views!
	}

	/** Set the height to a new value. Must be between 0 and MAX_HEIGHT. */
	public void setHeight(double theHeight) {
		this.height = Math.min(Math.max(0, theHeight), MAX_SIDE);

		this.updateAllViews(); // update Views!
	}

	/** Set both the base and the height to new values. */
	public void setValues(double theBase, double theHeight) {
		this.base = Math.min(Math.max(0, theBase), MAX_SIDE);
		this.height = Math.min(Math.max(0, theHeight), MAX_SIDE);

		this.updateAllViews(); // update Views!
	}

	/** Get the length of this triangle's base. */
	public double getBase() {
		return this.base;
	}

	/** Get this triangle's height. */
	public double getHeight() {
		return this.height;
	}

	/** Get the length of this triangle's hypotenuse. */
	public double getHypotenuse() {
		return Math.sqrt(this.base * this.base + this.height * this.height);
	}

	/** Add a new view of this triangle. */
	public void addView(IView view) {
		this.views.add(view);

		view.updateView(); // update Views!
	}

	/** Remove a view from this triangle. */
	public void removeView(IView view) {
		this.views.remove(view);
	}

	/** Update all the views that are viewing this triangle. */
	private void updateAllViews() {
		for (IView view : this.views) {
			view.updateView();
		}
	}
}
