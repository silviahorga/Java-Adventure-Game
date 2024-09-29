import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

/**
 * Bullet object.
 */
public class code_Bullet extends JPanel {
    public double x;
    public double y;
    double velx;
    double vely;
    int source;

    /**
     * Bullet Object constructor.
     * @param startx The Start position X of the bullet.
     * @param starty The Start position Y of the bullet.
     * @param velX  The velocity X of the bullet.
     * @param velY The velocity X of the bullet.
     * @param theSource The source of the bullet 0 being the player and 1 the enemies
     */
    public code_Bullet(int startx, int starty, double velX, double velY, int theSource) {
        velx = velX;
        vely = velY;
        x = startx;
        y = starty;
        source = theSource;
        //System.out.println(startx +" "+starty +" " + velx+" "+vely);

    }

    /**
     * Update the bullets position.
     */
    public void move() {
        x += velx;
        y += vely;
    }

    /**
     * Draw the bullet with different color according to the source.
     * @param g the graphics
     */
    public void draw(Graphics g) {
        //System.out.println("DRAW");
        Graphics2D g2d = (Graphics2D) g;
        if (source == 0){
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.RED);
        }
        g2d.fillRect((int) x, (int) y, 10, 10);
    }

}
