
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The class represents the Legendary Magic Hat that the player has to reach.
 * The class has methods that render the hat and the winning announcement
 * and that check if the player has reached it or not.
 */
public class code_LegendaryHat {
    code_MainGame game;
    BufferedImage hatimage;
    BufferedImage endimage;
    int x;
    int y;
    public boolean wonGame;

    /**
     * The constructor of the hat initializes the position of the hat in the game
     * world
     * as well as the hat image.
     */
    public code_LegendaryHat(code_MainGame game) {
        this.game = game;
        try {
            hatimage = ImageIO.read(new File("img_hat.png"));
            endimage = ImageIO.read(new File("img_final.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = 4 * 48;
        y = 9900;

    }

    /**
     * The function renders the hat image relative to the player position and the
     * screen.
     */
    public void draw(Graphics2D g2) {
        int wy = code_MainGame.SCREENHEIGHT / 2 - 24;
        int screenY = -y + game.manager.getWorldY() + wy;

        g2.drawImage(hatimage, x, screenY, null);
    }

    /**
     * The function checks if the player has reached the legendary magic hat.
     * If so, the function announces that the game has been won by rendering an
     * image.
     */
    public void gotHat(Graphics2D g2, code_Player player) {
        if (player.x >= 4 * 48 - 5 && player.x < 6 * 48 + 5
            && player.y > (-y + game.manager.getWorldY() + code_MainGame.SCREENHEIGHT / 2 - 24) - 5
            && player.y < (-y + game.manager.getWorldY() + code_MainGame.SCREENHEIGHT / 2 - 24) + 100) {
            wonGame = true;
            g2.drawImage(endimage, null, 0, 0);
        }
    }
}
