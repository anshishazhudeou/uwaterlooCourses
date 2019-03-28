import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WidgetDemo {

	public static void main(String[] args) {
		WidgetDemo demo = new WidgetDemo();
	}
	
	JLabel label;
	
	WidgetDemo()
	{
		JFrame f = new JFrame("WidgetDemo.java");
		f.setSize(500, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// create a button and add a listener for events
		JButton button = new JButton("My Button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label.setText("button");
			}
		});
		
		// create a slider
		JSlider slider = new JSlider(0, 100, 50);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider s = (JSlider)e.getSource();
				label.setText("slider " + s.getValue());
			}
		});
		
		// create a radio button group
		ButtonGroup radiobuttons = new ButtonGroup();
		JPanel radioPanel = new JPanel(new GridLayout(1, 0));
		for (String s: new String[] {"A", "B", "C"})
		{
			JRadioButton rb = new JRadioButton(s);
			rb.addActionListener(radioButtonListener);
			radiobuttons.add(rb);
			radioPanel.add(rb);
		}
		
		// create a menu
		JMenu menu = new JMenu("Choices");		
		// create some menu choices
		for (String s: new String[] {"apple", "orange", "banana", "pear" })
		{
			// add this menu item to the menu
			JMenuItem mi = new JMenuItem(s);
			// set the listener when events occur
			mi.addActionListener(menuItemListener);
			// add this menu item to the menu
			menu.add(mi);
		}
		JMenuBar menubar = new JMenuBar();
		menubar.add(menu);

		// create a label
		// (we'll use this to display messages)
		label = new JLabel("message");
		// set some properties to customize how it looks
		label.setPreferredSize(new Dimension(500, 60));
		label.setHorizontalAlignment( SwingConstants.CENTER );
		label.setBackground(Color.WHITE);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setFont(new Font("SansSerif", Font.PLAIN, 30));
		
		
		// define the layout
		// http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html

		
		// add the widgets
		f.add(button);
		f.add(slider);
		f.add(menubar);
		f.add(radioPanel);
		f.add(label);

		f.setLayout(new FlowLayout());
				
		f.setVisible(true);
	}
	
	// create a menu item listener
	MenuItemListener menuItemListener = new MenuItemListener();
	
	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JMenuItem mi = (JMenuItem)e.getSource();
			label.setText(mi.getText());
		}
	}
	
	// create a radio button listener
	RadioButtonListener radioButtonListener = new RadioButtonListener();

	class RadioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JRadioButton rb = (JRadioButton)e.getSource();
			label.setText(rb.getText());
		}
	}
	
}
