import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 * The object represents the player of the game.
 */
public class code_Player {
    public int x;
    public int y;

    private BufferedImage up1;
    private BufferedImage down1;
    private BufferedImage down2;
    private BufferedImage left1;
    private BufferedImage left2;
    private BufferedImage right1;
    private BufferedImage right2;

    private int spriteCounter = 0;
    private int spriteNumber = 1;

    private double velx = 0;
    private double vely = 0;

    private static int upperBound = 400;
    private int lowerBound;

    private int bulletSpeed = 18;
    // public ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private double acceleration = 7;
    private double friction = 0.5;
    private double maxSpeed = 7;

    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;
    // LIFE
    public static int maxLife = 6;
    public int life;
    // public Heart hearts;
    private code_MainGame game;

    /**
     * The player constructor.
     * The constructor initializes the life of the player and the x and y coordonate of the player.
     * The constructor calls the function getPlayerImage().
     */
    public code_Player(code_MainGame game) {
        this.game = game;
        lowerBound = code_MainGame.SCREENHEIGHT - 40;
        // LIFE
        life = maxLife;
        // INITIAL PLAYER POSITION IN WORLD
        x = code_MainGame.SCREENWIDTH / 2 - 24; // Initial x-position
        y = code_MainGame.SCREENHEIGHT / 2 - 24; // Initial y-position
        getPlayerImage();
    }

    /**
     * The function renders the character sprite that corresponds to the player movement.
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = down1;
        if (up) {
            image = up1;
        }
        if (down) {
            if (spriteNumber == 1) {
                image = down1;
            } else {
                image = down2;
            }
        }
        if (left) {
            if (spriteNumber == 1) {
                image = left1;
            } else {
                image = left2;
            }
        }
        if (right) {
            if (spriteNumber == 1) {
                image = right1;
            } else {
                image = right2;
            }
        }

        g2.fillRect(x, y, 5, 5);
        g2.drawImage(image, x - 23, y - 25, null);
    }
    
    /**
     * The function initializes the player sprites.
     */
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File("img_pl_up.png"));
            down1 = ImageIO.read(new File("img_pl_down.png"));
            down2 = ImageIO.read(new File("img_pl_down_blink.png"));
            left1 = ImageIO.read(new File("img_pl_left_stay.png"));
            left2 = ImageIO.read(new File("img_pl_left_walk.png"));
            right1 = ImageIO.read(new File("img_pl_right_stay.png"));
            right2 = ImageIO.read(new File("img_pl_right_walk.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void shoot(int targetx, int targety) {
        game.bh.shoot(x, y, targetx, targety, bulletSpeed, 0);
    }

    /**
     * This function return a rectangle around the player. It is used for collisions.
     * @return A rectangle object with the dimensinons and position of the player
     */
    public Rectangle getPlayerRect() {
        Rectangle playerRect = new Rectangle(x, y, 48, 48);
        return (playerRect);
    }
    
    /**
     * The function deals with determining the coordonates at which the player has to be drawn
     *  on the screen in order to create the movement effect.
     */
    public void move() {
        if (this.life == 0) {
            game.stopGame();
        }

        // System.out.println("MOVE");
        if (right) {
            velx += acceleration;
        }
        if (left) {
            velx -= acceleration;
        }

        if (up) {
            vely -= acceleration;
        }
        if (down) {
            vely += acceleration;
        }
        if (Math.abs(velx) > 1) {
            velx *= (1 - friction);
        } else {
            velx = 0;
        }
        if (Math.abs(vely) > 1) {
            vely *= (1 - friction);
        } else {
            vely = 0;
        }

        double speed = Math.sqrt(velx * velx + vely * vely);
        if (speed > maxSpeed) {
            double scale = maxSpeed / speed;
            velx *= scale;
            vely *= scale;
        }
        int nextY;
        int nextX;
        if (vely >= 0) {
            nextY = y + (int) (Math.ceil(vely));
        } else {
            nextY = y + (int) (Math.floor(vely));
        }
        if (velx >= 0) {
            nextX = x + (int) (Math.ceil(velx));
        } else {
            nextX = x + (int) (Math.floor(velx));
        }
        // System.out.print("\033[H\033[2J");

        // See if the tile that the player is going in is a wall (If the number in the
        // tileMap is in the WallNumList)
        if (Arrays.binarySearch(code_TileManager.wallNumList, game.manager.getTileAt(nextX, y)) >= 0) {
            velx = 0;
        }

        if (Arrays.binarySearch(code_TileManager.wallNumList, game.manager.getTileAt(x, nextY)) >= 0) {
            vely = 0;
        }

        if (nextY <= upperBound && !(game.manager.getWorldY() > 10270)) {
            game.moveDown((int) maxSpeed);
            vely = 0;
            y = upperBound + 1;
        }
        if (nextY >= lowerBound && !(Arrays.binarySearch(code_TileManager.wallNumList,
                game.manager.getTileAt(x, (int) (y + maxSpeed))) >= 0)) {
            game.moveUp((int) maxSpeed);
            y = lowerBound - 1;
            vely = 0;
        }

        y += vely;
        x += velx;

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }

        if (game.manager.heartPos[0] == game.manager.getTileXY(x, y)[1]
                && game.manager.heartPos[1] == game.manager.getTileXY(x, y)[0] - 1) {
            game.manager.removeHeart();
            System.out.println("LIFE");
            life += 2;
        }
    }
}