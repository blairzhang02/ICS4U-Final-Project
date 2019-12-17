/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighter;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author blzha5634
 */
public class DrawingSurface extends JPanel {

    private Image background;
    private final int DELAY = 1;
    private Timer timer;
    DamageFighter f1;
    ActionListener al;

    public DrawingSurface() {
        
        loadImage();
        init();
        
    }

    private void init() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        f1 = new DamageFighter("f1", 100, 10, 5, 1, 20, 350);
        timer = new Timer(DELAY, al);
        timer.start();
    }

    private void loadImage() {
        background = new ImageIcon("src//fighter//background.gif").getImage();
        setSurfaceSize();
    }

    private void setSurfaceSize() {
        Dimension d = new Dimension();
        d.width = background.getWidth(null);
        d.height = background.getHeight(null);
        setPreferredSize(d);
    }

    private void doDrawing(Graphics g) {
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;
        //draw a string on the panel   
        g2d.drawImage(background, 0, 0, this);
        f1.draw(g2d, f1.getX(), f1.getY());
        f1.update();
        //System.out.println(f1.getX());
    }

    private void step() {
        f1.update();
        //repaint();
    }

   // public void actionPerformed(ActionEvent e) {
        
    //    step();
   // }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            f1.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            f1.keyPressed(e);
        }
    }

    //overrides paintComponent in JPanel class
    //performs custom painting
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        doDrawing(g);
    }

}
