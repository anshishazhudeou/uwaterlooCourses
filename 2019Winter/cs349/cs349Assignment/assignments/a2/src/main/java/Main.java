package main.java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// refer some code from module 10 mvc





public class Main {
    public static void main(String[] args) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        // create Model and initialize it
        Model model = new Model();


        JFrame frame = new JFrame("Liang");

        // add menu bar to the main frame
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");


        // for tool
        // create View, tell it about model
        PalettesToolView palettesToolView = new PalettesToolView(model);
        // tell Model about View.
        model.addView(palettesToolView);


        // for canvas
        // create Model and initialize it
        // create View, tell it about model
        PalettesCanvasView view = new PalettesCanvasView(model);
        // tell Model about View.
        model.addView(view);


        // add new, load, save to the menua bar and their corresponding listener

        // menu Item new
        JMenuItem menuItemNew = new JMenuItem("New");
        menuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.newDrawing();
            }
        });
        menuFile.add(menuItemNew);

        // menu Item load
        JMenuItem menuItemLoad = new JMenuItem(("Load"));
        menuItemLoad.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();

                int returnVal = fileChooser.showOpenDialog(frame);

                if (returnVal == JFileChooser.APPROVE_OPTION){

                    // this is where a real application would open the file
                    File file = fileChooser.getSelectedFile();
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject("");
                        objectOutputStream.close();
                    }catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }));
        menuFile.add(menuItemLoad);


        // menu Item save
        JMenuItem menuItemSave = new JMenuItem("Save");
        menuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showSaveDialog(frame);
                File file = fileChooser.getSelectedFile();
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject("");
                    objectOutputStream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        menuFile.add(menuItemSave);

        menuBar.add(menuFile);
        frame.setJMenuBar(menuBar);

        Container drawingContainer= frame.getContentPane();
        drawingContainer.setLayout(new BoxLayout(drawingContainer, BoxLayout.X_AXIS));

        // tool
        drawingContainer.add(palettesToolView);
        //canvas
        drawingContainer.add(view);

        frame.setPreferredSize(new Dimension(1600, 1000));
        frame.setMaximumSize(new Dimension(1600, 1200));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setVisible(true);

    }

    // Handle ESC


}
