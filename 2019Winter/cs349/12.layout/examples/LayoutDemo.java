
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/* Demonstrate various layout managers available in Java, 
 * including defining your own layout manager.
 * 
 * @author Byron Weber Becker
 */
public class LayoutDemo {
	public static void main(String[] args) {
		// These are stacked bottom to top, so put the first one
		// to be discussed last.
		new LayoutDemoFrame("RandomLayout", new DemoRandomLayout());
		new LayoutDemoFrame("SpringLayout", new DemoSpringLayout());
		new LayoutDemoFrame("GridBagLayout", new DemoGridBagLayout());
		new LayoutDemoFrame("BorderLayout", new DemoBorderLayout());
		new LayoutDemoFrame("BoxLayout3", new DemoBoxLayout3());
		new LayoutDemoFrame("BoxLayout2", new DemoBoxLayout2());
		new LayoutDemoFrame("BoxLayout1", new DemoBoxLayout1());
		new LayoutDemoFrame("GridLayout", new DemoGridLayout());
		new LayoutDemoFrame("FlowLayout", new DemoFlowLayout());
		new LayoutDemoFrame("NullLayout", new DemoNullLayout());
	}
}

class LayoutDemoFrame extends JFrame {
	private static int xPos = 10;
	private static int yPos = 10;
	private static final int OFFSET = 50;

	public LayoutDemoFrame(String title, JPanel contents) {
		super(title);
		this.setContentPane(contents);
		this.setSize(300, 130);
		// set this to keep the JFrame from shrinking too small
		//this.setMinimumSize(new Dimension(150,  100));
		this.setLocation(xPos, yPos);
		xPos += OFFSET;
		yPos += OFFSET;
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}


// give positions and sizes for all widgets
class DemoNullLayout extends JPanel {
	public DemoNullLayout() {
		this.setBackground(Color.DARK_GRAY);
		
		// clear the layout
		this.setLayout(null);

		// Add the components 
		JButton b1 = new JButton("Button1");
		JButton b2 = new JButton("Button2");
		b1.setBounds(10, 20, 150, 50); // x, y, width, height
		b2.setBounds(40, 60, 100, 30);
		this.add(b2);
		this.add(b1);
	}
}


class DemoFlowLayout extends JPanel {
	public DemoFlowLayout() {
		this.setBackground(Color.DARK_GRAY);

		// note also FlowLayout(int alignment, int horizontalSpace, int verticalSpace);
		this.setLayout(new FlowLayout());

		// Add the components 
		this.add(new JButton("  One  "));
		this.add(new JTextField("  Two  ", 10));
		this.add(new JButton("  Three  "));
		this.add(new JButton("  Four  "));
	}
}


class DemoGridLayout extends JPanel {
	public DemoGridLayout() {
		this.setBackground(Color.DARK_GRAY);

		// Set the layout strategy to a grid with 2 rows and 3 columns.
		this.setLayout(new GridLayout(2, 3));

		// Add the components 
		// (always added in left-to-right, top-to-bottom reading order )
		this.add(new JButton("One"));
		this.add(new JButton("Two"));
		this.add(new JButton("Three"));
		this.add(new JButton("Four"));
		this.add(new JButton("Five"));
//		this.add(new JButton("Six"));
	}
}


class DemoBoxLayout1 extends JPanel {
	// Default behaviour of BoxLayout
	public DemoBoxLayout1() {
		this.setBackground(Color.DARK_GRAY);

		// use BoxLayout
		// try BoxLayout.X_AXIS as well 
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Add the components 
		this.add(new JButton("One"));
		this.add(new JButton("Two"));
		this.add(new JTextArea("TextArea"));
		this.add(new JButton("Three"));
	}
}

class DemoBoxLayout2 extends JPanel {
	public DemoBoxLayout2() {
		this.setBackground(Color.DARK_GRAY);

		// use BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// Add the components 
		this.add(new JButton("One"));
		this.add(new JButton("Two"));		
		JTextArea ta = new JTextArea("TextArea");
		// Text areas have infinite maximum size by default, so BoxLayout will
		// grow it to fill all available space unless we place a limit.
//		ta.setMaximumSize(new Dimension(200, 150));
		this.add(ta);
		this.add(new JButton("Three"));
		this.add(new JButton("Four"));
		this.add(new JButton("Five"));
	}
}

class DemoBoxLayout3 extends JPanel {
	// Using rigid areas, struts and glue
	public DemoBoxLayout3() {
		this.setBackground(Color.DARK_GRAY);

		// use BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Add the components 
		this.add(new JButton("One"));
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(Box.createVerticalGlue());
		this.add(new JButton("Two"));
		this.add(Box.createVerticalStrut(10));
		this.add(new JButton("Three"));
	}
}


class DemoBorderLayout extends JPanel {
	public DemoBorderLayout() {
		this.setBackground(Color.DARK_GRAY);

		// use BorderLayout
		this.setLayout(new BorderLayout());

		// Add the components 
		this.add(new JButton("North"), BorderLayout.NORTH);
		this.add(new JButton("East"), BorderLayout.EAST);
		this.add(new JButton("West"), BorderLayout.WEST);
		
		// Layouts can be nested ...
		
		// Box is an easy-to-create JPanel with a BoxLayout
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalGlue());
		b.add(new JButton("Ok"));
		b.add(Box.createHorizontalStrut(20));
		b.add(new JButton("Cancel"));
		
		this.add(b, BorderLayout.SOUTH);
		
		// nesting a previous demo panel ...
		this.add(new DemoBoxLayout1(), BorderLayout.CENTER);
	}
}



class DemoGridBagLayout extends JPanel {
	public DemoGridBagLayout() {
		this.setBackground(Color.DARK_GRAY);

		// use GridBagLayout
		this.setLayout(new GridBagLayout());
		
		// create a constraints object
		GridBagConstraints gc = new GridBagConstraints();
		// stretch the widget horizontally and vertically
		gc.fill = GridBagConstraints.BOTH;
		gc.gridwidth = 1; // 1 grid cell wide
		gc.gridheight = 3; // 3 grid cells tall
		gc.weightx = 0.5; // the proportion of space to give this column
		gc.weighty = 0.5; // the proportion of space to give this row
		this.add(new JButton("One"), gc);
		
		// modify gc for the next widget to be added
		gc.fill = GridBagConstraints.VERTICAL;
		gc.gridwidth = 1; 
		gc.gridheight = 1;
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;
		this.add(new JButton("Two"), gc);
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		this.add(new JButton("Three"), gc);
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.EAST;
		this.add(new JButton("Four"), gc);
		// add something spanning across the bottom
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 2;
		this.add(new JButton("Five"), gc);
	}
}

class DemoSpringLayout extends JPanel {
	private JButton b1 = new JButton("b1 - - - - ");
	private JTextArea t1 = new JTextArea("t1");
	private JButton b2 = new JButton("b2");

	public DemoSpringLayout() {
		this.setBackground(Color.DARK_GRAY);

		// use SpringLayout
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.add(this.b1);
		this.add(this.t1);
		this.add(this.b2);

		// Add constraints to b1
		// constrain west side of b1 to be 10 pixels from west side of the panel
		layout.putConstraint(SpringLayout.WEST, b1, 10, 
			SpringLayout.WEST, this);
		// constrain north side of b1 to be 10 pixels from north side of the panel
		layout.putConstraint(SpringLayout.NORTH, b1, 10, 
			SpringLayout.NORTH, this);



		// Add constraints to b2
		// constrain b2 to stay 10 pixels from left of panel
		layout.putConstraint(SpringLayout.WEST, b2, 10, 
			SpringLayout.WEST, this);		
		// constrain b2 to stay 10 pixels from bottom of panel
		layout.putConstraint(SpringLayout.SOUTH, b2, -10, 
			SpringLayout.SOUTH, this);

		// constrain east side of b2 to be east side of b1		
		layout.putConstraint(SpringLayout.EAST, b2, 0, 
			SpringLayout.EAST, b1);


		
		// Add constraints to t1
		// Let the textarea grow as large as requested
		t1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
			Integer.MAX_VALUE));

		// constrain t1 to be east of the buttons and spread vertically to fill
		// the entire height of the panel
		layout.putConstraint(SpringLayout.WEST, t1, 10, 
			SpringLayout.EAST, b1);
		layout.putConstraint(SpringLayout.NORTH, t1, 10, 
			SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, t1, -10, 
			SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, t1, -10, 
			SpringLayout.EAST, this );
	}
}


class DemoRandomLayout extends JPanel {

	public DemoRandomLayout() {
		this.setBackground(Color.DARK_GRAY);

		// use custom layout
		this.setLayout(new RandomLayout());

		this.add(new JButton("One"));
		this.add(new JButton("Two"));
		this.add(new JButton("Three"));
		this.add(new JButton("Four"));
		this.add(new JButton("Five"));
		this.add(new JButton("Six"));

	}
}

// a custom layout manager
class RandomLayout implements LayoutManager {
    public void addLayoutComponent(String name, Component comp) {
        // no-op
    }
    public void layoutContainer(Container container) {
        Component[] components = container.getComponents();
        Dimension parentSize = container.getSize();
        
        for (int i = 0; i < components.length; i++) {
            Component c = components[i];
            c.setSize(c.getPreferredSize());
            int x = (int)(Math.random() * (parentSize.width - c.getWidth()));
            int y = (int)(Math.random() * (parentSize.height - c.getHeight()));
            c.setLocation(x, y);
        }
    }
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(400, 300);
    }
    public Dimension preferredLayoutSize(Container parent) {
        return this.minimumLayoutSize(parent);
    }
    public void removeLayoutComponent(Component comp) {
        // no-op
    }
}

