
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This object represents the NPC.
 * The object has methods that render the NPC and the dialogue with the NPC.
 */
public class code_NPC {

    code_MainGame game;
    BufferedImage imageNPC;
    BufferedImage textimage;
    int x;
    int y;
    String text = new String();
    
    /**
     * The npc constructor initializes the NPC coordonates in the world and the NPC image.
     */
    public code_NPC(code_MainGame game) {
        this.game = game;
        try {
            imageNPC = ImageIO.read(new File("img_npc_wizard.png"));
            textimage = ImageIO.read(new File("img_npc_txt_start.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = 4 * 48;
        y = 2 * 48;

    }

    /**
     * The draw function renders the NPC image relative to the position of the player.
     */
    public void draw(Graphics2D g2) {
        int wy = code_MainGame.SCREENHEIGHT / 2 - 24; // Initial y-position
        int screenY = -y + game.manager.getWorldY() + wy;
        g2.drawImage(imageNPC, x, screenY, null);
    }

    /**
     * The function renders the dialogue text box of the NPC upon 
     *  the player getting within 5 pixels to the NPC image.
     */
    public void dialogue(code_Player player, Graphics2D g2) {
        if (player.x >= 4 * 48 - 5 && player.x < 6 * 48 + 5
            && player.y > (-y + game.manager.getWorldY() + code_MainGame.SCREENHEIGHT / 2 - 24) - 5
            && player.y < (-y + game.manager.getWorldY() + code_MainGame.SCREENHEIGHT / 2 - 24) + 100) {
            int screenx = 24;
            int screeny = 60;
            int width = code_MainGame.SCREENWIDTH - code_MainGame.TILESIZE;
            int height = code_MainGame.TILESIZE * 5 + 20;
            // System.out.println("THE HEIGHT IS"+height);
            Color c = new Color(0, 0, 0);
            g2.setColor(c);
            g2.fillRoundRect(screenx, screeny, width, height, 35, 35);
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(screenx + 5, screeny + 5, width - 10, height - 10, 35, 35);
            g2.drawImage(textimage, null, screenx + 10, screeny + 10);
        }

    }
}
