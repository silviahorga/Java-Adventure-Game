import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 * The enemy object.
 */
public class code_Enemy2 implements ActionListener {
    BufferedImage imageEnemy;
    code_Player targetPlayer;
    public int x;
    public int y;
    public int bulletSpeed = 15;
    public int life;
    public int maxLife;
    private Timer timer1;
    private Timer timer2;


    boolean firstInteraction = true;
    public ArrayList<code_Bullet> bullets = new ArrayList<code_Bullet>();
    code_BulletHandler bh;

    /**
     * The constructor for the enemy object.
     * @param startX The X of the enemy
     * @param startY The Y of the enemy
     * @param targetPlayer The player that the enemy is targeting
     * @param shootingDelay the time interval of each enemy shot
     * @param bh the bullet handler that will handle the enemy's bullets
     */
    public code_Enemy2(int startX, int startY, code_Player targetPlayer, 
        int shootingDelay, code_BulletHandler bh) {
        
        this.bh = bh;
        this.targetPlayer = targetPlayer;
        x = startX;
        y = startY;
        maxLife = 2;
        life = maxLife;
        getImage();


        timer1 = new Timer(200, this);
        timer1.start();
        timer2 = new Timer(shootingDelay, this);
        
    }

    /**
     * This function gets called for the enemy to shoot. 
     * If the enemy is on-screen, it shoots
     */
    public void actionPerformed(ActionEvent e) {
        //System.out.println(dy);
        if (y < code_MainGame.SCREENHEIGHT && y > 0) {
            if (firstInteraction) {
                firstInteraction = false;
                timer1.stop();
                timer2.start();
            }
            shoot();
        }

    }

    /**
     * This stops the shooting timer.
     */
    public void stopTimer() {
        timer2.stop();
    }

    /**
     * Each time the enemy shoots, this calls the shoot function of 
     * the game's bullet handler to create the bullet.
     */
    public void shoot() {
        bh.shoot(x, y, targetPlayer.x, targetPlayer.y, bulletSpeed, 1);
    }

    /**
     * This loads the image of the enemy.
     */
    public void getImage() {
        try {
            imageEnemy = ImageIO.read(new File("img_enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * This function draws the image of the enemy at its position.
     * this is called by the enemy handler each time the game calls its drawEnemies function. 
     * @param g2 the 2d graphics.
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(imageEnemy, x, y, null);

    }

    
}