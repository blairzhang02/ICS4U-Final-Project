/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

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
    protected int x, y, xSpeed, ySpeed;

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
        x += xSpeed * mvmtSpeed;
        y += ySpeed * mvmtSpeed;
    }

    public void draw(Graphics2D g2d, int nX, int nY) {

        g2d.setColor(Color.BLACK);
        g2d.fillRect(nX, nY, 50, 120);
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

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            xSpeed = -2;
            
        }
        if (key == KeyEvent.VK_RIGHT) {
            xSpeed = 2;
        }

        if (key == KeyEvent.VK_UP && y >= 280) {

            ySpeed = -2;

        }

        if (key == KeyEvent.VK_DOWN) {
            ySpeed = 2;
        }
       
    }

    public void fall() {
        ySpeed = 2;
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            xSpeed = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            xSpeed = 0;
            
        }

        if (key == KeyEvent.VK_UP) {

           
              ySpeed = 0;
              
        }

        if (key == KeyEvent.VK_DOWN) {
            ySpeed = 0;
        }

    }

    /**
     * Mutator for the name of the character
     *
     * @param n - the new name
     */
    public void setName(String n) {
        name = n;
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
    public int getXSpeed() {
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
    public int getYSpeed() {
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
