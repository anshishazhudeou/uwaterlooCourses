import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;

public class MarkingMenu extends JComponent {

    // save MenuItem
    ArrayList<JMenuItem> jMenuItems = new ArrayList<>();

    // control open level 2
    private boolean isOpenSubMenu = false;

    // level 1 menu
    private Popup markingMenu;

    // level 2 menu
    private Popup subMarkingMenu;

    // inner classes marking menu ui
    class MarkingMenuUI  extends JPanel{

        private final int radius = 80;
        private Ellipse2D.Double circle;
        private List<Double> lines = new ArrayList<>();

        private Graphics2D g2;

        private List<JMenuItem> items;

        private int level;

        // ui paint
        public void paint(Graphics g) {
            super.paintComponent(g);
            g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(Color.BLACK);

            // Draw the marking menu : Circle  Lines
            g2.setColor(new Color(190,194, 198));
            g2.fill(circle);
            g2.setColor(Color.BLACK);
            g2.draw(circle);

            for (Double line : lines) {
                g2.draw(line);
            }

            for (int i = 0; i < items.size(); i++) {
                // Draw item names for each item
                g2.drawString(
                    items.get(i).getText(),
                    (int) (radius + (45 * Math.cos((((2 * Math.PI) / items.size()) * i) + (Math.PI / items.size())))),
                    (int) (radius + (45 * Math.sin((((2 * Math.PI) / items.size()) * i) + (Math.PI / items.size()))))
                );
            }
        }

        MarkingMenuUI (int x, int y, List<JMenuItem> items, int level) {
            this.items = items;
            this.level = level;

            this.circle = new Ellipse2D.Double(0,  0, 2 * radius, 2 * radius);
            this.getLines();

            setSize(2*radius, 2*radius);
            setPreferredSize(new Dimension(2*radius, 2*radius));
            setLocation(x ,y);
        }

        /* get lines in marking menu circle  */
        private void getLines() {
            for (int i = 0; i < items.size(); i++) {
                lines.add(new Double(
                    radius,
                    radius,
                    (radius + radius * Math.cos((i * (2 * Math.PI / items.size())))),
                    (radius + radius * Math.sin((i * (2 * Math.PI / items.size()))))
                ));
            }
        }
        public int getRadius() {
            return radius;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    MarkingMenu(){
    }

    public void add(JMenuItem jMenuItem) {
        if (jMenuItems.size() == 8) {
            System.out.println("level menu is full");
            return;
        }

        jMenuItems.add(jMenuItem);
    }

    private Popup openMenu(Component invoker, int x, int y, List<JMenuItem> jMenuItems, int level) {

        // create menu UI
        MarkingMenuUI menuUI = new MarkingMenuUI(x, y, jMenuItems, level);

        // add mouse event
        menuUI.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // if mouse left click
                if (e.getButton() == MouseEvent.BUTTON1) {
                     System.out.println("click marking menu");

                    // mouse point in circle
                    if (e.getPoint().distance(menuUI.getRadius(), menuUI.getRadius()) <= menuUI.getRadius()) {

                        // if current menu is click , submenu open, close submenu
                        if (isOpenSubMenu && ((MarkingMenuUI) e.getComponent()).getLevel() == 1) {
                            subMarkingMenu.hide();
                            isOpenSubMenu = false;
                            return;
                        }

                        // Calculation mouse point angle
                        double angle = -Math.toDegrees(Math.atan2(e.getY() - menuUI.getRadius(), e.getX() - menuUI.getRadius()));
                        if (angle < 0)
                            angle += 360;
                        angle = 360 - angle;

                        int currentIndex = -1;
                        // mouse point mapping JMenuItem
                        for (int i = 0; i < jMenuItems.size(); i++) {
                            if (angle >= i * 360 / jMenuItems.size() &&
                                angle < (i + 1) * 360 / jMenuItems.size()) {
                                currentIndex = i;
                                break;
                            }
                        }
                        System.out.println(currentIndex);
                        System.out.println("name: " + jMenuItems.get(currentIndex).getText());

                        JMenuItem jMenuItem = jMenuItems.get(currentIndex);
                        // if submenu no open, and JMenuItem is JMenu, and Jmenu hava JMenuItem
                        if (jMenuItem instanceof JMenu && !isOpenSubMenu && ((JMenu) jMenuItem).getItemCount() != 0) {
                            // open submenu
                            ArrayList<JMenuItem> subItems = new ArrayList<>();
                            for (int i=0; i < ((JMenu) jMenuItem).getItemCount(); i ++) {
                                subItems.add(((JMenu) jMenuItem).getItem(i));
                            }
                            subMarkingMenu = openMenu(e.getComponent(), e.getX(), e.getY(), subItems, 2);
                            isOpenSubMenu = true;
                        }
                        else {
                            // perform JMenuItem Action
                            for (ActionListener actionListener: jMenuItem.getActionListeners()) {
                                actionListener.actionPerformed(new ActionEvent(actionListener,
                                    ActionEvent.ACTION_PERFORMED, "SelectSubMenu"));
                            }
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
//                System.out.println("mousePressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                System.out.println("mouseReleased");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                System.out.println("mouseEntered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                System.out.println("mouseExited");
            }
        });

        Point show = new Point(0, 0);
        SwingUtilities.convertPointToScreen(show, invoker);
        // open popup ui
        Popup pop = PopupFactory.getSharedInstance().getPopup(invoker, menuUI, show.x+x, show.y+y);
        pop.show();
        return pop;
    }

    public void show(Component invoker, int x, int y) {
        // open menu
        markingMenu = openMenu(invoker, x, y, jMenuItems, 1);
        // parent component mouse click listener
        invoker.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    markingMenu.hide();
                    if (isOpenSubMenu) {
                        subMarkingMenu.hide();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
