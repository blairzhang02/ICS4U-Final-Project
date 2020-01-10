
package totalfighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
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
    protected boolean fall = false, jump = false, ground = true, punch = false, kick = false;
    
    protected boolean movingLeft = false, movingRight = false;
    protected Graphics2D g;
    protected boolean stand = true;
    protected boolean walk = false;

    protected boolean left = false;

    protected boolean right = false;
    protected boolean directionRight = true;
    protected int pos = 150;
    protected long stime;
    protected long etime;
    protected long punchFrame = 0;
    protected long kickFrame = 0;

    //movement
    protected BufferedImage[] standingSprites;
    protected totalfighter.Animate standing;
    protected BufferedImage[] punchingSprites;
    protected totalfighter.Animate punching;
    protected BufferedImage[] kickingSprites;
    protected totalfighter.Animate kicking;
    protected BufferedImage[] jumpingSprites;
    protected totalfighter.Animate jumping;
    protected BufferedImage[] walkForSprites;
    protected totalfighter.Animate walkingFor;
    protected BufferedImage[] walkBackSprites;
    protected totalfighter.Animate walkingBack;

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
    public FighterClass(String nName, int nHealth, int nDamage, int nMvmtSpeed, int nX, int nY, boolean isDamageFighter) {
        this();
        name = nName;
        health = nHealth;
        damage = nDamage;
        mvmtSpeed = nMvmtSpeed;
        x = nX;
        y = nY;
        xSpeed = 0;
        ySpeed = 0;
        if (isDamageFighter) {
            BufferedImage[] stand = {totalfighter.Sprite.loadSprite("Standing1"), totalfighter.Sprite.loadSprite("Standing2"), totalfighter.Sprite.loadSprite("Standing3"), totalfighter.Sprite.loadSprite("Standing4")};
            standingSprites = stand;
            standing = new totalfighter.Animate(standingSprites, 5);
            BufferedImage[] punch = {totalfighter.Sprite.loadSprite("Punch2"), totalfighter.Sprite.loadSprite("Punch3"), totalfighter.Sprite.loadSprite("Punch4"), totalfighter.Sprite.loadSprite("Punch5"), totalfighter.Sprite.loadSprite("Punch5"), totalfighter.Sprite.loadSprite("Punch5")};
            punchingSprites = punch;
            punching = new totalfighter.Animate(punchingSprites, 5);
            BufferedImage[] jump = {totalfighter.Sprite.loadSprite("Jump1"), totalfighter.Sprite.loadSprite("Jump2"), totalfighter.Sprite.loadSprite("Jump3"), totalfighter.Sprite.loadSprite("Jump4"), totalfighter.Sprite.loadSprite("Jump5"), totalfighter.Sprite.loadSprite("Jump6"), totalfighter.Sprite.loadSprite("Jump7")};
            jumpingSprites = jump;
            jumping = new totalfighter.Animate(jumpingSprites, 5);
            BufferedImage[] walkF = {totalfighter.Sprite.loadSprite("ForwardWalk1"), totalfighter.Sprite.loadSprite("ForwardWalk2"), totalfighter.Sprite.loadSprite("ForwardWalk3"), totalfighter.Sprite.loadSprite("ForwardWalk4"), totalfighter.Sprite.loadSprite("ForwardWalk5"), totalfighter.Sprite.loadSprite("ForwardWalk6")};
            walkForSprites = walkF;
            walkingFor = new totalfighter.Animate(walkForSprites, 5);
            BufferedImage[] walkB = {totalfighter.Sprite.loadSprite("BackwardWalk1"), totalfighter.Sprite.loadSprite("BackwardWalk2"), totalfighter.Sprite.loadSprite("BackwardWalk3"), totalfighter.Sprite.loadSprite("BackwardWalk4"), totalfighter.Sprite.loadSprite("BackwardWalk5"), totalfighter.Sprite.loadSprite("BackwardWalk6")};
            walkBackSprites = walkB;
            walkingBack = new totalfighter.Animate(walkBackSprites, 5);
            BufferedImage[] kick = {totalfighter.Sprite.loadSprite("HighKick1"), totalfighter.Sprite.loadSprite("HighKick2"), totalfighter.Sprite.loadSprite("HighKick3"), totalfighter.Sprite.loadSprite("HighKick3"), totalfighter.Sprite.loadSprite("HighKick3")};
            kickingSprites = kick;
            kicking = new totalfighter.Animate(kickingSprites, 5);

        } else {
            BufferedImage[] stand = {totalfighter.Sprite.loadSprite("gokuStand1"), totalfighter.Sprite.loadSprite("gokuStand2"), totalfighter.Sprite.loadSprite("gokuStand3"), totalfighter.Sprite.loadSprite("gokuStand4")};
            standingSprites = stand;
            standing = new totalfighter.Animate(standingSprites, 5);
            BufferedImage[] punch = {totalfighter.Sprite.loadSprite("gokuPunch1"), totalfighter.Sprite.loadSprite("gokuPunch2"),totalfighter.Sprite.loadSprite("gokuPunch3"), totalfighter.Sprite.loadSprite("gokuPunch4"), totalfighter.Sprite.loadSprite("gokuAltPunch1"), totalfighter.Sprite.loadSprite("gokuAltPunch2"), totalfighter.Sprite.loadSprite("gokuAltPunch3"), totalfighter.Sprite.loadSprite("gokuAltPunch4")};
            punchingSprites = punch;
            punching = new totalfighter.Animate(punchingSprites, 5);
            BufferedImage[] jump = {totalfighter.Sprite.loadSprite("gokuJump1"), totalfighter.Sprite.loadSprite("gokuJump2"), totalfighter.Sprite.loadSprite("gokuJump3"), totalfighter.Sprite.loadSprite("gokuJump4"), totalfighter.Sprite.loadSprite("gokuJump5"), totalfighter.Sprite.loadSprite("gokuJump6"), totalfighter.Sprite.loadSprite("gokuJump7")};
            jumpingSprites = jump;
            jumping = new totalfighter.Animate(jumpingSprites, 5);
            BufferedImage[] walkF = {totalfighter.Sprite.loadSprite("gokuBackwardWalk2")};
            walkForSprites = walkF;
            walkingFor = new totalfighter.Animate(walkForSprites, 5);
            BufferedImage[] walkB = {totalfighter.Sprite.loadSprite("gokuForwardWalk2")};
            walkBackSprites = walkB;
            walkingBack = new totalfighter.Animate(walkBackSprites, 5);
            BufferedImage[] kick = {totalfighter.Sprite.loadSprite("gokuKick1"), totalfighter.Sprite.loadSprite("gokuKick2"), totalfighter.Sprite.loadSprite("gokuKick3"), totalfighter.Sprite.loadSprite("gokuKick4"), totalfighter.Sprite.loadSprite("gokuKick5"), totalfighter.Sprite.loadSprite("gokuKick6")};
            kickingSprites = kick;
            kicking = new totalfighter.Animate(kickingSprites, 5);

        }
    }

    public void update() {
        checkBound();
        if (movingLeft) {
            xSpeed = -6;
        } else if (movingRight) {
            xSpeed = 6;
        } else if (movingLeft == false && movingRight == false) {

            xSpeed = 0;
        }
        x += xSpeed * mvmtSpeed;
        checkBound();

        checkJump();

        y += ySpeed * mvmtSpeed;
        checkGround();
        
        standing.update();
        jumping.update();
        walkingBack.update();
        walkingFor.update();
        if (punchFrame < 50) {
            punching.update();
            punchFrame += 1;
        } else {
            punching.stop();
            punchFrame = 0;
            punch = false;
            punching.reset();
        }
        if (kickFrame < 50) {
            kicking.update();
            kickFrame += 1;
        } else {
            kicking.stop();
            kickFrame = 0;
            kick = false;
            kicking.reset();
        }
    }
    public void punch(boolean start) {
        if (start) {
            stand = false;
            punching.start();
        } else {
            stand = true;
            punch = false;
            punching.stop();
        }
        
        
        
    }
    
    public void kick(boolean start) {
        if (start) {
            stand = false;
            kicking.start();
        } else {
            stand = true;
            kick = false;
            kicking.stop();
        }
    }


    public void checkBound() {
        System.out.println(x);
        if (x <= 16) {
            xSpeed = 0;
            x = 17;
        }
        if (x >= 906) {
            xSpeed = 0;
            x = 905;
        }
    }

    public void checkJump() {

        checkGround();

        if (jump == true || jump == false && ground == false) {
            stand = false;
            if (y >= 160) {
                ySpeed = -8;

            } else {
                ground = false;
                fall = true;
            }

        }
        if (fall == true) {
            stand = false;
            checkGround();

            if (ground == false) {
                ySpeed = 8;
                checkGround();
            } else {
                ySpeed = 0;
                fall = false;
                ground = true;
                stand = true;
            }

        }
    }

    public void checkGround() {
        if (y == 360) {
            jumping.stop();
            if (right) {
                right = true;
            } else if (left) {
                left = true;
            } else {
                stand = true;
            }
            standing.start();
            ground = true;
        } else {
            ground = false;
        }
    }
    
    public void changeDirection() {
        pos = pos * (-1);
    }
    
    public void walking() {
        checkGround();
        stand = false;
        standing.stop();
        if (pos > 0) { //facing right
            if (right) {
                walkingFor.start();
            }
            if (left) {
                walkingBack.start();
            }
        }
    }

    public void draw(Graphics2D g2d, int nX, int nY) {
        g = g2d;
        checkGround();
        g.drawRect(nX+20,nY-15,100,120);
        if (punch) {
            stime = System.currentTimeMillis();
            punch(true);
            g.drawImage(punching.getSprite(), nX, nY - 35, pos, 150, null);
            g.drawRect(nX+120,nY-5,30,20);
        }else if (kick) {
            stime = System.currentTimeMillis();
            kick(true);
            g.drawImage(kicking.getSprite(), nX, nY - 30, pos, 150, null);
            g.drawRect(nX+120,nY-5,40,20);
        } else if (stand) {
            standing.start();
            g.drawImage(standing.getSprite(), nX, nY - 20, pos, 135, null);
            
            
        } else if (jump || jump == false && ground == false) {
            checkJump();
            standing.stop();
            jumping.start();
            g.drawImage(jumping.getSprite(), nX, nY - 20, pos, 150, null);

        } else if (walk) {
            walking();
            if (pos > 0) { //facing right
                if (right) {
                    g.drawImage(walkingFor.getSprite(), nX, nY - 20, pos, 150, null);
                }
                if (left) {

                    g.drawImage(walkingBack.getSprite(), nX, nY - 20, pos, 150, null);
                }
            }

        }
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

    public boolean isStand() {
        return stand;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }

    public boolean isWalk() {
        return walk;
    }

    public void setWalk(boolean walk) {
        this.walk = walk;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isKick() {
        return kick;
    }

    public void setKick(boolean kick) {
        this.kick = kick;
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
