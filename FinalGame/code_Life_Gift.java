

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This object represents the player gifts.
 */
public class code_Life_Gift {
    code_TileManager manager;
    BufferedImage heartImage;
    
    int tileX;
    int tileY;
    
    /**
     * The player gifts constructor.
     * The constructor initializes the position of the gift relative to
     *  the player and the game world and the heart gift image.
     */
    public code_Life_Gift(int tileX, int tileY, code_TileManager manager) { 
        this.tileX = tileX;
        this.tileY = tileY;
        this.manager = manager;
        try {
            heartImage = ImageIO.read(new File("img_hrt_fullheart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  

    /**
     * This fuction draws the life gift at its coordinates.
     * It uses the getPosFromTile function to determine its position on the screen.
     * @param g2 the 2d Graphics
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(heartImage, manager.getPosFromTile(tileX, tileY)[0],
             manager.getPosFromTile(tileX, tileY)[1], null);
    }
}
