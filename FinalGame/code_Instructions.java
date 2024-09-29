import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This is the interactable instructions class.
 * Each time the player gets near the Instructions board, instructions appear on
 * screen.
 */
public class code_Instructions {
    code_MainGame game;
    BufferedImage imageInstructions;
    BufferedImage textimage;
    int x;
    int y;

    /**
     * The constructor of te Instructions board.
     * 
     * @param game the MainGame Object
     */
    public code_Instructions(code_MainGame game) {
        this.game = game;
        try {
            imageInstructions = ImageIO.read(new File("img_npc_instructions.png"));
            textimage = ImageIO.read(new File("img_npc_txt_instructions.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = 1 * 48;
        y = 1 * 48;

    }

    /**
     * This draws the Instructions board on the screen.
     */
    public void draw(Graphics2D g2) {
        int wy = code_MainGame.SCREENHEIGHT / 2 - 24;
        int screenY = -y + game.manager.getWorldY() + wy;
        g2.drawImage(imageInstructions, x, screenY, null);
    }

    /**
     * If the player is near the instruction board, the instructions appear on the
     * screen.
     * 
     * @param player the player object
     * @param g2     the graphics
     */
    public void showInstructions(code_Player player, Graphics2D g2) {
        if (player.x >= 1 * 48 - 5
            && player.x <= 3 * 48 + 5
            && player.y > (-y + game.manager.getWorldY() + code_MainGame.SCREENHEIGHT / 2 - 24) - 5
            && player.y < (-y + game.manager.getWorldY() + code_MainGame.SCREENHEIGHT / 2 - 24) + 100) {

            int screenx = 48 * 2;
            int screeny = 48;
            int width = code_MainGame.SCREENWIDTH - code_MainGame.TILESIZE * 4;
            int height = code_MainGame.TILESIZE * 4;
            // System.out.println("THE HEIGHT IS"+height);
            Color c = new Color(0, 0, 0);
            g2.setColor(c);
            g2.fillRoundRect(screenx, screeny, width, height, 35, 35);
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(screenx + 5, screeny + 5, width - 10, height - 10, 35, 35);
            g2.drawImage(textimage, null, screenx + 10, screeny + 15);
        }

    }

}
