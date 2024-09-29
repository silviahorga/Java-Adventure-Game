import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This object represents the player lives.
 */
public class code_Heart {
    public static BufferedImage full;
    public static BufferedImage half;
    public static BufferedImage empty;
    public static int x = 48 / 2;
    public static int y = 48 / 2;

    /**
     * The function initializes the 3 heart images and draws the hearts on the
     *  screen according to the current player life.
     */
    public static void displayhearts(Graphics2D g2, int life){
        try {
            full = ImageIO.read(new File("img_hrt_fullheart.png"));
            half = ImageIO.read(new File("img_hrt_halfheart.png"));
            empty = ImageIO.read(new File("img_hrt_emptyheart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //DRAW EMPTY HEART(we draw the rest above this hearts)
        int i = 0;
        while (i < Math.floor(life / 2)) {
            g2.drawImage(full, x + i * 48, y, null);
            i++;
        }
        if(life % 2 != 0) {
            g2.drawImage(half, x + i * 48, y, null);
            i++;
        }
        while(i < code_Player.maxLife / 2) {
            g2.drawImage(empty, x + i * 48, y, null);
            i++;
        }
    }
    
}
