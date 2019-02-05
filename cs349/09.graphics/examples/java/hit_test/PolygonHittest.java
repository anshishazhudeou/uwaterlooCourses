/*
* CS 349 Java Code Examples
*
* PolygonHittest  Uses built-in method to hit-test closed polygon.
*
*/
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class PolygonHittest extends JPanel{

	Point M = new Point(); // mouse point
	Polygon poly = new Polygon();

	public static void main(String args[]){
		JFrame window = new JFrame("PolygonHittest");
		window.setSize(300, 300);
		window.setContentPane(new PolygonHittest());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public PolygonHittest(){
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				poly.addPoint(e.getX(), e.getY());
				repaint();
			}
		});

        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                M.x = e.getX();
                M.y = e.getY();
                repaint();
            }
        }); 

	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;  

		if (poly.contains(M.x, M.y))
			g2.setColor(Color.BLUE);
		else
			g2.setColor(Color.RED);

        g2.fillPolygon(poly);

		g2.setColor(Color.BLACK);
		g.drawPolyline(poly.xpoints, poly.ypoints, poly.npoints);

	}
}
