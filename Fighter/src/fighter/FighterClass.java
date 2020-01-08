/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author blzha5634
 */
abstract public class FighterClass implements Character {

    //protected attributes of a character
    protected String name;
    protected int health;
    protected int damage;
    protected int mvmtSpeed; //movement speed
    protected int x, y;
    protected double xSpeed, ySpeed;
    protected boolean fall = false, jump = false, ground = true, punch = false;
    protected boolean movingLeft = false, movingRight = false;
    protected Graphics2D g;

    /**
     * Primary Constructor of a character
     */
    public FighterClass() {
        name = "";
        health = 0;
        damage = 0;
        mvmtSpeed = 0;
        xSpeed = 0;
        ySpeed = 0;
    }

    /**
     * Secondary Constructor of a character
     *
     * @param nName - the name of the character
     * @param nHealth - the health of the character
     * @param nDamage - the damage stat of the character
     * @param nMvmtSpeed - the movement speed of the character
     * @param nX - the x position of the character
     * @param nX - the y position of the character
     */
    public FighterClass(String nName, int nHealth, int nDamage, int nMvmtSpeed, int nX, int nY) {
        this();
        name = nName;
        health = nHealth;
        damage = nDamage;
        mvmtSpeed = nMvmtSpeed;
        x = nX;
        y = nY;
        xSpeed = 0;
        ySpeed = 0;
    }

    public void update() {
        checkBound();
        if(movingLeft || movingRight){
            x += xSpeed * mvmtSpeed;
        }
        
        checkBound();

        checkJump();
        y += ySpeed * mvmtSpeed;

    }

    public void punch(Graphics g) {

    }

    public void checkBound() {
        if (x <= 16) {
            xSpeed = 0;
            x = 17;
        }
        if (x >= 916) {
            xSpeed = 0;
            x = 915;
        }
    }

    public void checkJump() {

        checkGround();

        if (jump == true && ground == true) {
            if (y >= 200) {
                ySpeed = -8;
            } else {
                jump = false;
                ground = false;
                fall = true;
            }
        }

        if (fall == true) {
            checkGround();
            if (ground == false) {
                ySpeed = 8;
                checkGround();
            } else {
                ySpeed = 0;
                fall = false;
                ground = true;
            }

        }
    }

    public void checkGround() {
        if (y == 360) {
            ground = true;
        }
    }

    public void draw(Graphics2D g2d, int nX, int nY) {
        g = g2d;
        g.setColor(Color.BLACK);
        g.fillRect(nX, nY, 50, 120);
    }

    //accessors and mutators
    /**
     * Accessor for the name of the character
     *
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

    //dont need this for now
    public boolean checkPlayer(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP || key == KeyEvent.VK_N || key == KeyEvent.VK_M) {
            return true;
        }
        return false;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }
    
    /**
    public void keyPressed(KeyEvent e, Boolean mainPlayer) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP || key == KeyEvent.VK_N || key == KeyEvent.VK_M || key == KeyEvent.VK_A || key == KeyEvent.VK_D || key == KeyEvent.VK_W || key == KeyEvent.VK_G || key == KeyEvent.VK_F) {
            pressed.add(key);
        }

        if (mainPlayer == true) {
         
            if (key == KeyEvent.VK_LEFT) {
                xSpeed = -4;
            }
            if (key == KeyEvent.VK_RIGHT) {

                xSpeed = 4;
            }

            if (key == KeyEvent.VK_UP) {
                jump = true;
            }
            if (key == KeyEvent.VK_N) {
            }
            if (key == KeyEvent.VK_M) {
                punch = true;
            }

        } else {
            if (key == KeyEvent.VK_A) {
                System.out.println(x);
                xSpeed = -4;
            }
            if (key == KeyEvent.VK_D) {

                xSpeed = 4;
            }

            if (key == KeyEvent.VK_W) {
                jump = true;
            }
            if (key == KeyEvent.VK_F) {
            }
            if (key == KeyEvent.VK_G) {
                punch = true;
            }
        }

    }

    public void keyReleased(KeyEvent e, Boolean mainPlayer) {

        int key = e.getKeyCode();
        if (mainPlayer == true) {
            if (key == KeyEvent.VK_LEFT) {
                xSpeed = 0;

            }

            if (key == KeyEvent.VK_RIGHT) {
                xSpeed = 0;
                
            }

            if (key == KeyEvent.VK_UP) {
               
            }
            if (key == KeyEvent.VK_N) {
               
            }
        } else if (mainPlayer == false) {
            if (key == KeyEvent.VK_A) {

                xSpeed = 0;
               
            }

            if (key == KeyEvent.VK_D) {
                xSpeed = 0;
               
            }

            if (key == KeyEvent.VK_W) {
                
            }
            if (key == KeyEvent.VK_B) {
              
            }

        }
    }
    **/
    /**
     * Mutator for the name of the character
     *
     * @param n - the new name
     */
    public void setName(String n) {
        name = n;
    }

    public boolean getFall() {
        return fall;
    }

    public void setFall(boolean fallStat) {
        fall = fallStat;
    }

    public boolean getJump() {
        return jump;
    }

    public void setJump(boolean jumpStat) {
        jump = jumpStat;
    }

    public boolean getPunch() {
        return punch;
    }

    public void setPunch(boolean punchStat) {
        punch = punchStat;
    }

    /**
     * Accessor for the health of the character
     *
     * @return the health of the character
     */
    public int getHealth() {
        return health;
    }

    /**
     * Mutator for the health of the character
     *
     * @param h - the new health
     */
    public void setHealth(int h) {
        health = h;
    }

    /**
     * Accessor for the damage of the character
     *
     * @return the damage power of the character
     */
    public int getdamage() {
        return damage;
    }

    /**
     * Mutator for the damage of the character
     *
     * @param d - the new damage
     */
    public void setDamage(int d) {
        damage = d;
    }

    /**
     * Accessor for the movement speed of the character
     *
     * @return the movement speed of the character
     */
    public int getSpeed() {
        return mvmtSpeed;
    }

    /**
     * Mutator for the movement speed of the character
     *
     * @param nSpeed - the new speed
     */
    public void setSpeed(int nSpeed) {
        mvmtSpeed = nSpeed;
    }

    /**
     * Accessor for the x position of the character
     *
     * @return the x position of the character
     */
    public int getX() {
        return x;
    }

    /**
     * Mutator for the x position of the character
     *
     * @param nX - the new x position
     */
    public void setX(int nX) {
        
        x = nX;
    }

    /**
     * Accessor for the y position of the character
     *
     * @return the y position of the character
     */
    public int getY() {
        return y;
    }

    /**
     * Mutator for the y position of the character
     *
     * @param nY - the new y position
     */
    public void setY(int nY) {
        y = nY;
    }

    /**
     * Accessor for the x position of the character
     *
     * @return the x position of the character
     */
    public double getXSpeed() {
        return xSpeed;
    }

    /**
     * Mutator for the x position of the character
     *
     * @param nX - the new x position
     */
    public void setXSpeed(int nX) {
        xSpeed = nX;
    }

    /**
     * Accessor for the y position of the character
     *
     * @return the y position of the character
     */
    public double getYSpeed() {
        return ySpeed;
    }

    /**
     * Mutator for the y position of the character
     *
     * @param nY - the new y position
     */
    public void setYSpeed(int nY) {
        ySpeed = nY;
    }

    @Override
    /**
     * toString - prints out all information of the fighter
     */
    public String toString() {
        String str;
        str = "Fighter{" + "Name: " + name
                + ", Health: " + health + ", Damage:"
                + damage + ", Movement Speed: " + mvmtSpeed + '}';
        return str;
    }

}
