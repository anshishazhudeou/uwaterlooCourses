package view;

import java.awt.LayoutManager;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

/**
 * A layout manager that arranges components in a double column. In most cases
 * the left column will hold a label and the right column will hold a component
 * the user can manipulate. Preferred component sizes are respected as much as
 * possible. Components in the left column are right justified; components in
 * the right column are left justified. <img src="doc-files/FormLayout.gif">
 * 
 * @author Byron Weber Becker
 */
public class FormLayout implements LayoutManager {
	private int hGap = 6;
	private int vGap = 4;

	/** Construct a new FormLayout object. */
	public FormLayout() {
		super();
	}

	/**
	 * Construct a new FormLayout object.
	 * 
	 * @param hGap
	 *            the number of hortizontal pixels between components
	 * @param vGap
	 *            the number of vertical pixels between components
	 */
	public FormLayout(int hGap, int vGap) {
		super();
		this.hGap = hGap;
		this.vGap = vGap;
	}

	/**
	 * Adds the specified component with the specified name to the layout.
	 * 
	 * @param name
	 *            the component name
	 * @param comp
	 *            the component to be added
	 */
	public void addLayoutComponent(String name, Component comp) {
	}

	/**
	 * Removes the specified component from the layout.
	 * 
	 * @param comp
	 *            the component to be removed
	 */
	public void removeLayoutComponent(Component comp) {
	}

	/**
	 * Calculates the preferred size dimensions for the specified panel given
	 * the components in the specified parent container.
	 * 
	 * @param parent
	 *            the component to be laid out
	 * 
	 * @see #minimumLayoutSize
	 */
	public Dimension preferredLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			ColDims cd = colDims(parent, true);

			Insets insets = parent.getInsets();
			Dimension d = new Dimension(cd.left + cd.right + insets.left
					+ insets.right, cd.height + insets.top + insets.bottom);
			return d;
		}
	}

	/*
	 * Precondition: the caller has gotten the treelock. preferred = true for
	 * preferred sizes; false for minimum sizes
	 */
	private ColDims colDims(Container parent, boolean preferred) {
		ColDims cd = new ColDims();
		int nComponents = parent.getComponentCount();
		// left column
		for (int i = 1; i < nComponents; i += 2) {
			Component left = parent.getComponent(i - 1);
			Component right = parent.getComponent(i);

			Dimension dLeft;
			Dimension dRight;
			if (preferred) {
				dLeft = left.getPreferredSize();
				dRight = right.getPreferredSize();
			} else {
				dLeft = left.getMinimumSize();
				dRight = right.getMinimumSize();
			}
			cd.left = (int) Math.max(cd.left, dLeft.width);
			cd.right = (int) Math.max(cd.right, dRight.width);
			cd.height += (int) Math.max(dLeft.height, dRight.height);
			cd.height += this.vGap;
		}
		if (nComponents % 2 == 1) { // odd number of components -- get the last
									// one on the left
			Component left = parent.getComponent(nComponents - 1);

			Dimension dLeft;
			if (preferred) {
				dLeft = left.getPreferredSize();
			} else {
				dLeft = left.getMinimumSize();
			}
			cd.left = (int) Math.max(cd.left, dLeft.width);
			cd.height += dLeft.height + this.vGap;
		}
		cd.left += this.hGap / 2;
		cd.right += this.hGap / 2;
		return cd;
	}

	/**
	 * Calculates the minimum size dimensions for the specified panel given the
	 * components in the specified parent container.
	 * 
	 * @param parent
	 *            the component to be laid out
	 * @see #preferredLayoutSize
	 */
	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			ColDims cd = colDims(parent, false);

			Insets insets = parent.getInsets();
			Dimension d = new Dimension(cd.left + cd.right + insets.left
					+ insets.right, cd.height + insets.top + insets.bottom);
			return d;
		}
	}

	/**
	 * Lays out the container in the specified panel.
	 * 
	 * @param parent
	 *            the component which needs to be laid out
	 */
	public void layoutContainer(Container parent) {
		Insets insets = parent.getInsets();

		synchronized (parent.getTreeLock()) {
			ColDims cd = this.colDims(parent, true);

			int desiredWidth = cd.left + cd.right + insets.left + insets.right;
			double widthScale = Math.min(1.0, parent.getWidth()
					/ (double) desiredWidth);
			double heightScale = Math.min(1.0, parent.getHeight()
					/ (double) cd.height);

			double scale = Math.min(widthScale, heightScale);

			// System.out.println("FormLayout.layoutContainer: widthScale = " +
			// widthScale + "; heightScale = " + heightScale);

			int midPt = (int) (((cd.left + insets.left + this.hGap) / (double) (cd.left
					+ cd.right + insets.left + insets.right + hGap)) * parent
					.getWidth());
			int top = insets.top + this.vGap;

			int nComponents = parent.getComponentCount();
			for (int i = 1; i < nComponents; i += 2) {
				Component left = parent.getComponent(i - 1);
				Component right = parent.getComponent(i);
				Dimension lDim = left.getPreferredSize();
				Dimension rDim = right.getPreferredSize();

				int rowHeight = (int) (Math.max(lDim.height, rDim.height) * heightScale);

				// scale left side, if necessary; then position
				if (lDim.width > desiredWidth || lDim.height > rowHeight) {
					lDim.width = (int) (lDim.width * scale);
					lDim.height = (int) (lDim.height * scale);
				}
				left.setBounds((midPt - lDim.width - this.hGap / 2), top,
						lDim.width, lDim.height);

				// scale left side, if necessary; then position
				if (rDim.width > desiredWidth || rDim.height > rowHeight) {
					rDim.width = (int) (rDim.width * scale);
					rDim.height = (int) (rDim.height * scale);
				}
				right.setBounds(midPt + this.hGap / 2, top, rDim.width,
						rDim.height);
				top = top + rowHeight + this.vGap;
			}
			if (nComponents % 2 == 1) { // odd number of components -- get the
										// last one on the left
				Component left = parent.getComponent(nComponents - 1);
				Dimension lDim = left.getPreferredSize();
				lDim.width = (int) (lDim.width * scale);
				lDim.height = (int) (lDim.height * scale);
				left.setBounds((midPt - lDim.width - this.hGap / 2), top,
						lDim.width, lDim.height);
			}
		}
	}

	private static class ColDims {
		int left = 0;
		int right = 0;
		int height = 0;
	}
}
