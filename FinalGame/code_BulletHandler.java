import java.awt.Graphics2D;
import java.util.ArrayList;

public class code_BulletHandler {
    code_MainGame game;

    /**
     * The constructor for the BulletHandler.
     */
    code_BulletHandler(code_MainGame game) {
        this.game = game;
    }

    public ArrayList<code_Bullet> bullets = new ArrayList<code_Bullet>();

    /**
     * This function is used to generate a bullet from a specific point to a
     * specific target.
     * 
     * @param x           the x from which the bullet is shot.
     * @param y           the y from which the bullet is shot.
     * @param targetX     the target x which the bullet must hit.
     * @param targetY     the target y which the bullet must hit.
     * @param bulletSpeed the speed that the bullet should have.
     * @param source      the source of the bullet: 0 is the player and 1 is the
     *                    enemies.
     */
    public void shoot(int x, int y, int targetX, int targetY, int bulletSpeed, int source) {
        // System.out.println("SHOOT");
        double dx = targetX - x;
        double dy = targetY - y;

        double angle = Math.atan2(dy, dx);
        double bulletVelX = Math.cos(angle) * (bulletSpeed);
        double bulletVelY = Math.sin(angle) * bulletSpeed;
        code_Bullet bullet = new code_Bullet(x, y, bulletVelX, bulletVelY, source);
        bullets.add(bullet);
    }

    /**
     * This function draws all the bullets in the screen.
     * @param g2 the 2d graphics
     */
    public void drawBullets(Graphics2D g2) {
        for (int i = 0; i < bullets.size(); i++) {
            code_Bullet bullet = bullets.get(i);
            bullet.move();
            bullet.draw(g2);
        }
    }
    
    /** 
     * This function moves all the bullets up. When the map moves up, 
     * all the bullets have to be affected aswell.
     */
    public void moveUp(double speed) {
        for (int i = 0; i < bullets.size(); i++) {
            code_Bullet bullet = bullets.get(i);
            bullet.y -= speed;
        }
    }

    /** 
     * This function moves all the bullets down. When the map moves down, 
     * all the bullets have to be affected aswell.
     */
    public void moveDown(double speed) {
        for (int i = 0; i < bullets.size(); i++) {
            code_Bullet bullet = bullets.get(i);
            bullet.y += speed;
        }
    }
}
