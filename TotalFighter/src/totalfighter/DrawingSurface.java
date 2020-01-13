/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package totalfighter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author blzha5634
 */
public class DrawingSurface extends JPanel implements ActionListener {

    private Image background;
    private Image healthBar1;
    private Image healthBar2;
    private final int DELAY = 10;
    private long kickStartTime1,punchStartTime1, kickStartTime2, punchStartTime2;
    private Timer timer;
    private long jumpingTime = 200;
    private boolean punchCoolDown1 = false, kickCoolDown1 = false, punchCoolDown2 = false, kickCoolDown2 = false;
    ArrayList keys = new ArrayList();
    DamageFighter f1;
    HealthFighter f2;
    ActionListener al;

    public DrawingSurface() {

        loadImage();
        init();

    }

    private void init() {
        addKeyListener(new TAdapter());
        setFocusable(true);

        f1 = new DamageFighter("f1", 100, 10, 1, 1, 20, 360, true, true);
        f2 = new HealthFighter("f2", 100, 10, 1, 1, 867, 360, false, false);
        timer = new Timer(DELAY, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {

        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();

    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, this);
        g2d.drawImage(healthBar1, 0, 0, this);
        g2d.drawImage(healthBar2, this.getWidth() - healthBar2.getWidth(null), 0, this);
        g2d.setColor(Color.green);
        g2d.fillRect(55, 18, 150, 18);
        g2d.fillRect(this.getWidth() - healthBar2.getWidth(null) + 15, 18, 150 * (f2.getHealth() / 100), 18);
        f1.draw(g2d, f1.getX(), f1.getY());
        f2.draw(g2d, f2.getX(), f2.getY());
        if (f1.getX() - f2.getX() > 0) {
            f1.setInitalDirection(false);
            f2.setInitalDirection(true);

        } else {
            f1.setInitalDirection(true);
            f2.setInitalDirection(false);
        }
        

        checkCollision();
        repaint();
    }

    public void checkCollision() {

        if (f1.getX() + 120 >= f2.getX() && f1.getX() < f2.getX()) {

        }

    }

    private void loadImage() {
        /**
         * //loading a random background image int r = (int) (Math.random() * 5
         * + 1);
         *
         * switch (r) { case 1: background = new
         * ImageIcon("src//totalfighter//backgrounds//indoorbackground.gif").getImage();
         * break; case 2: background = new
         * ImageIcon("src//totalfighter//backgrounds//templebackground.gif").getImage();
         * break; case 3: background = new
         * ImageIcon("src//totalfighter//backgrounds//dragonbackground.gif").getImage();
         * break; case 4: background = new
         * ImageIcon("src//totalfighter//backgrounds//stormbackground.gif").getImage();
         * break; default: background = new
         * ImageIcon("src//totalfighter//backgrounds//bridgebackground.gif").getImage();
         * break; }
         *
         */
        background = new ImageIcon("src//totalfighter//backgrounds//bridgebackground.gif").getImage();
        //loading health bar images
        healthBar1 = new ImageIcon("src//totalfighter//healthBar.png").getImage();
        healthBar2 = new ImageIcon("src//totalfighter//healthBarP2.png").getImage();

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
        f2.update();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                f2.setMovingLeft(false);

            }

            if (key == KeyEvent.VK_RIGHT) {
                f2.setMovingRight(false);
            }

            if (key == KeyEvent.VK_UP) {
                f2.setJump(false);

            }
            if (key == KeyEvent.VK_DOWN) {
                f2.setCrouch(false);

            }
            if (key == KeyEvent.VK_N) {

            }
            if (key == KeyEvent.VK_M) {
                f2.setPunch(false);
            }
            if (key == KeyEvent.VK_A) {
                f1.setMovingLeft(false);

            }

            if (key == KeyEvent.VK_D) {
                f1.setMovingRight(false);

            }
            if (key == KeyEvent.VK_S) {
                f1.setCrouch(false);

            }

            if (key == KeyEvent.VK_W) {
                f1.setJump(false);

            }
            if (key == KeyEvent.VK_G) {
                f1.setPunch(false);
            }

            if (key == KeyEvent.VK_F) {

            }

            if (key == KeyEvent.VK_N) {
            }

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                f2.setMovingLeft(true);
                f2.setWalk(true);
                f2.setLeft(true);
                f2.setStand(false);
                f2.setRight(false);
            }
            if (key == KeyEvent.VK_RIGHT) {

                f2.setMovingRight(true);
                f2.setWalk(true);
                f2.setLeft(false);
                f2.setStand(false);
                f2.setRight(true);
            }

            if (key == KeyEvent.VK_UP) {
                f2.setJump(true);

            }
            if (key == KeyEvent.VK_DOWN) {
                f2.setCrouch(true);

            }
            if (key == KeyEvent.VK_N) {
                if (kickCoolDown2 == false) {
                    f2.setKick(true);
                    kickCoolDown2 = true;
                    kickStartTime2 = System.currentTimeMillis();
                }

                if (kickCoolDown2 == true && (System.currentTimeMillis() - kickStartTime2) >= 1100) {

                    kickCoolDown2 = false;
                    f2.setKick(true);
                }
            }
            if (key == KeyEvent.VK_M) {
                if (punchCoolDown2 == false) {
                    f2.setPunch(true);
                    punchCoolDown2= true;
                    punchStartTime2 = System.currentTimeMillis();
                }

                if (punchCoolDown2 == true && (System.currentTimeMillis() - punchStartTime2) >= 700) {
                    
                    punchCoolDown2 = false;
                    f2.setPunch(true);

                }else if (punchCoolDown2 == true && (System.currentTimeMillis() - punchStartTime2) < 700) {
                    f2.setPunch(false);
                }

            }

            if (key == KeyEvent.VK_A) {
                f1.setMovingLeft(true);
                f1.setWalk(true);
                f1.setLeft(true);
                f1.setStand(false);
                f1.setRight(false);
            }
            if (key == KeyEvent.VK_D) {

                f1.setMovingRight(true);
                f1.setWalk(true);
                f1.setLeft(false);
                f1.setStand(false);
                f1.setRight(true);
            }

            if (key == KeyEvent.VK_W) {
                f1.setJump(true);
            }
            if (key == KeyEvent.VK_S) {
                f1.setCrouch(true);

            }
            if (key == KeyEvent.VK_F) {

                if (kickCoolDown1 == false) {
                    f1.setKick(true);
                    kickCoolDown1 = true;
                    kickStartTime1 = System.currentTimeMillis();
                }

                if (kickCoolDown1 == true && (System.currentTimeMillis() - kickStartTime1) >= 1100) {

                    kickCoolDown1 = false;
                    f1.setKick(true);
                }
            }

            if (key == KeyEvent.VK_G) {
                if (punchCoolDown1 == false) {
                    f1.setPunch(true);
                    punchCoolDown1 = true;
                    punchStartTime1 = System.currentTimeMillis();
                }
                System.out.println(System.currentTimeMillis() - punchStartTime1);
                if (punchCoolDown1 == true && (System.currentTimeMillis() - punchStartTime1) >= 700) {
                    
                    punchCoolDown1 = false;
                    f1.setPunch(true);

                }else if (punchCoolDown1 == true && (System.currentTimeMillis() - punchStartTime1) < 700) {
                 
                    f1.setPunch(false);
                }

            }

        }
    }
}
