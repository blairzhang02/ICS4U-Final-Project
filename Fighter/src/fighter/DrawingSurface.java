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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author blzha5634
 */
public class DrawingSurface extends JPanel implements ActionListener {

    private Image background;
    private final int DELAY = 10;
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

        f1 = new DamageFighter("f1", 100, 10, 1, 1, 20, 350);

        timer = new Timer(DELAY, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {

       // super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, this);
        f1.draw(g2d, f1.getX(), f1.getY());
        if (f1.getY() >= 350){
            f1.setY(350);
        }
        
        
        repaint();
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

    public void actionPerformed(ActionEvent e) {

        f1.update();

    }

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

}
