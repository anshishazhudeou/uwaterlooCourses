import java.awt.*;
import javax.swing.*;

import java.awt.LayoutManager;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

// give positions and sizes for all widgets
public class DemoAlignLayout extends JFrame {

	public static void main(String[] args) {
		DemoAlignLayout demo = new DemoAlignLayout();
	}

	DemoAlignLayout() {
		
		this.setSize(800, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// layout 
		this.setLayout(new AlignLayout(40, 40));

		// add components
		JComponent c;
		c = new JLabel("Number");
		// try uncommenting this
		// c.setPreferredSize(new Dimension(200, 50));
		debugBorder(c); 
		this.add(c);

		c = new JSlider(0, 100, 50);
		debugBorder(c);
		this.add(c);

		c = new JLabel("Choice");
		debugBorder(c); 
		this.add(c);

		// create a radio button group
		ButtonGroup radiobuttons = new ButtonGroup();
		JPanel radioPanel = new JPanel(new GridLayout(1, 0));
		for (String s: new String[] {"A", "B", "C"})
		{
			JRadioButton rb = new JRadioButton(s);
			radiobuttons.add(rb);
			radioPanel.add(rb);
		}
		debugBorder(radioPanel);
		this.add(radioPanel);

		System.out.println("Preferred size: " + this.getPreferredSize());
		System.out.println("Minimum size: " + this.getMinimumSize());

		this.setVisible(true);		
	}

	// I made this to make it easy to add some debug information
	void debugBorder(JComponent c) {
		// try uncommenting this
		c.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}



/**
 * A layout manager that arranges components in a horizontal line equally spaced 
 * and centred vertically in the parent container.
 */
class AlignLayout implements LayoutManager {

	int minimumSpacing;
	int preferredSpacing;

	/** Construct a new AlignLayout object with spacing between components. */
	public AlignLayout(int minSpacing, int preferredSpacing) {
		super();
		this.minimumSpacing = minimumSpacing;
		this.preferredSpacing = preferredSpacing;
	}

	// for specialized layouts, not used here
	public void addLayoutComponent(String name, Component comp) { }
	public void removeLayoutComponent(Component comp) {	}

	/**
	 * Calculates the preferred size dimensions for the specified panel given
	 * the components in the specified parent container.
	 */
	public Dimension preferredLayoutSize(Container parent) {

		synchronized (parent.getTreeLock()) {

			// get space needed for all children
			Dimension space = calculateSpace(parent, true);

			// this container's padding
			Insets insets = parent.getInsets();

			Dimension d = new Dimension(insets.left + space.width +insets.right, 
										insets.top + space.height + insets.bottom);
			return d;
		}
	}


	/**
	 * Calculates the minimum size dimensions for the specified panel given the
	 * components in the specified parent container.
	 */
	public Dimension minimumLayoutSize(Container parent) {

		synchronized (parent.getTreeLock()) {

			// get space needed for all children
			Dimension space = calculateSpace(parent, false);

			// this container's padding
			Insets insets = parent.getInsets();

			Dimension d = new Dimension(insets.left + space.width +insets.right, 
										insets.top + space.height + insets.bottom);
			return d;
		}
	}


	/**
	 * Lays out the container in the specified panel.
	 */
	public void layoutContainer(Container parent) {

		synchronized (parent.getTreeLock()) {

			// get space needed for all children (preferred)
			Dimension space = calculateSpace(parent, true);

			// this container's padding
			Insets insets = parent.getInsets();

			// get actual space available in parent
			int w = parent.getWidth() - insets.left - insets.right;
			int h = parent.getHeight() - insets.top - insets.bottom;

			System.out.println("layoutContainer (parent size " + w + "," + h + ")");

			// vertical centre line to layout component
			int y = h / 2;

			// starting x is left side of all components to lay out
			int x = (w - space.width) / 2;

			int nComponents = parent.getComponentCount();
			for (int i = 0; i < nComponents; i++) {

				Component c = parent.getComponent(i);

				Dimension d = c.getPreferredSize();

				c.setBounds(x, y - d.height / 2, d.width, d.height);

				x += d.width + preferredSpacing;
			}

		}
	}	


	/*
	 * Precondition: the caller has gotten the treelock.
	 */
	private Dimension calculateSpace(Container parent, boolean isPreferred) {

		// find total width for all components and
		// height of tallest component
		Dimension result = new Dimension(0,0);

		int nComponents = parent.getComponentCount();
		for (int i = 0; i < nComponents; i++) {

			Dimension d;
			if (isPreferred) {
				d = parent.getComponent(i).getPreferredSize();
			} else {
				d = parent.getComponent(i).getMinimumSize();
			}
			// update the total width and height required
			result.width += d.width;
			result.height = Math.max(result.height, d.height);
		}

		// add spacing in between components
		if (isPreferred) {
			result.width += (nComponents - 1) * preferredSpacing;
		} else {
			result.width += (nComponents - 1) * minimumSpacing;
		}		

		return result;
	}





}





