
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

/**
 * The object stores the game world pattern and renders the background
 * of the game relative to the position of the player.
 */
public class code_TileManager {
    code_Tile[] tile;
    private int worldY;
    private code_Life_Gift lifeGift;

    public static int[] wallNumList = { 2, 5, 9, 13, 17 };
    public int[] heartPos;

    public static int[][] tileMap = {
            { 17, 17, 17, 17, 17, 17, 17, 17, 17, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 21, 21, 21, 21, 16, 16, 17 },
            { 17, 16, 16, 16, 16, 16, 16, 16, 16, 17 },
            { 17, 16, 16, 16, 16, 16, 16, 16, 16, 17 },
            { 13, 15, 15, 15, 15, 15, 15, 15, 15, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 12, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 12, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 14, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 12, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 12, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 12, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 12, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 12, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 12, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 14, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 12, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 14, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 12, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 12, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 12, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 12, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 12, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 14, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 12, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 14, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 12, 11, 11, 13 },
            { 13, 11, 11, 11, 11, 11, 11, 11, 11, 13 },
            { 9, 10, 10, 10, 10, 10, 10, 10, 10, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 8, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 8, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 8, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 8, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 8, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 8, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 8, 7, 7, 7, 7, 7, 8, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 8, 8, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 8, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 8, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 8, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 8, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 8, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 8, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 9, 8, 7, 7, 7, 7, 7, 8, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 8, 8, 7, 9 },
            { 9, 7, 7, 7, 7, 7, 7, 7, 7, 9 },
            { 5, 6, 6, 6, 6, 6, 6, 6, 6, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 18, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 20, 20, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 20, 20, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 20, 4, 4, 4, 4, 18, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 18, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 18, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 18, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 18, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 18, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 18, 4, 4, 5 },
            { 5, 4, 18, 4, 4, 4, 4, 20, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 20, 20, 20, 5 },
            { 5, 4, 4, 4, 4, 4, 20, 20, 20, 5 },
            { 5, 18, 4, 4, 4, 20, 20, 20, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 18, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 20, 4, 4, 4, 18, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 18, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 18, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 20, 4, 4, 4, 4, 18, 4, 4, 5 },
            { 5, 20, 20, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 18, 20, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 4, 4, 4, 18, 4, 4, 20, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 18, 4, 4, 5 },
            { 5, 20, 18, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 20, 20, 4, 4, 4, 18, 4, 4, 5 },
            { 5, 4, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 5, 18, 4, 4, 4, 4, 4, 4, 4, 5 },
            { 2, 3, 3, 3, 3, 3, 3, 3, 3, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 19, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 1, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 1, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 1, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 1, 0, 0, 0, 0, 0, 19, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 19, 0, 2 },
            { 2, 0, 0, 0, 0, 1, 0, 0, 0, 2 },
            { 2, 0, 1, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 1, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 1, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 1, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 1, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 19, 0, 0, 0, 0, 1, 0, 2 },
            { 2, 19, 0, 19, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 1, 0, 0, 0, 19, 0, 2 },
            { 2, 1, 0, 0, 0, 0, 0, 0, 19, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 1, 0, 0, 2 },
            { 2, 0, 0, 1, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 1, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 1, 0, 0, 0, 0, 0, 19, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 19, 19, 2 },
            { 2, 0, 1, 0, 0, 0, 0, 0, 19, 2 },
            { 2, 0, 0, 0, 0, 1, 0, 0, 0, 2 },
            { 2, 19, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 1, 2 },
            { 2, 0, 0, 0, 0, 0, 1, 0, 0, 2 },
            { 2, 0, 1, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 1, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 1, 0, 0, 0, 0, 2 },
            { 2, 0, 0, 0, 0, 0, 0, 1, 0, 2 },
            { 2, 19, 0, 0, 19, 19, 0, 19, 19, 2 },
            { 2, 19, 19, 19, 19, 19, 19, 19, 19, 2 },
            { 2, 19, 19, 19, 19, 19, 19, 19, 19, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
    };

    boolean heartGiven = false;

    /**
     * The constructor builds the tile array, builds the heart gidt array and the
     * life gift array.
     * The constructor calls the function getTileImage();.
     */
    public code_TileManager() {
        worldY = 0;
        tile = new code_Tile[25];
        getTileImage();
        int randomTileX = ThreadLocalRandom.current().nextInt(1, 9);
        int randomTileY = ThreadLocalRandom.current().nextInt(50, 180 + 1);

        heartPos = new int[] { randomTileX, randomTileY };
        lifeGift = new code_Life_Gift(randomTileX, randomTileY, this);
    }

    /**
     * This function resets the position of the heart list, so the player can not
     * regain the heart.
     * it also sets the heart given bool to true so the game doesn't keep rendering
     * the heart.
     */
    public void removeHeart() {
        heartPos = new int[1];
        heartGiven = true;
    }

    public int getWorldY() {
        return worldY;
    }

    /**
     * The function returns the x and y coordonate that a certain tile
     * has to be drawn relative to the screen.
     */
    public int[] getPosFromTile(int tileX, int tileY) {
        int x = tileX * code_MainGame.TILESIZE;
        int y = (tileY + 1 - tileMap.length) * code_MainGame.TILESIZE 
            + code_MainGame.SCREENHEIGHT + worldY;
        return new int[] { x, y };
    }

    /**
     * This function receives the x and y coordinates in the screen
     * and returns which tile of the tileMap is in this position.
     * @param x the X coordinate in the screen
     * @param y the Y coordinate in the screen
     * @return The value of the tile
     */
    public int getTileAt(int x, int y) {
        int cellX = (x / code_MainGame.TILESIZE);
        int cellY = (tileMap.length - (code_MainGame.SCREENHEIGHT - y + worldY)
             / code_MainGame.TILESIZE) - 1;
        return (tileMap[cellY][cellX]);
    }

    /**
     * This function receives the x and y coordinates in the screen
     * and returns an array with the row and collunm of the tileMap array with this x and y.
     * @param x the X coordinate in the screen
     * @param y the Y coordinate in the screen
     * @return The value of the tile
     */
    public int[] getTileXY(int x, int y) {
        int cellX = (x / code_MainGame.TILESIZE);
        int cellY = (tileMap.length - (code_MainGame.SCREENHEIGHT - y + worldY) 
            / code_MainGame.TILESIZE) - 1;
        return (new int[] { cellY, cellX });
    }

    /**
     * The function creates the tile array elements and initializes the images of
     * each tile.
     */
    public void getTileImage() {
        try {
            tile[0] = new code_Tile();
            tile[0].image = ImageIO.read(new File("img_bg_1grass.png"));

            tile[1] = new code_Tile();
            tile[1].image = ImageIO.read(new File("img_bg_1flowers.png"));

            tile[2] = new code_Tile();
            tile[2].image = ImageIO.read(new File("img_bg_1wall.png"));

            tile[3] = new code_Tile();
            tile[3].image = ImageIO.read(new File("img_bg_1transition2.png"));

            tile[4] = new code_Tile();
            tile[4].image = ImageIO.read(new File("img_bg_2pink.png"));

            tile[5] = new code_Tile();
            tile[5].image = ImageIO.read(new File("img_bg_2wall.png"));

            tile[6] = new code_Tile();
            tile[6].image = ImageIO.read(new File("img_bg_2transition3.png"));

            tile[7] = new code_Tile();
            tile[7].image = ImageIO.read(new File("img_bg_3floor.png"));

            tile[8] = new code_Tile();
            tile[8].image = ImageIO.read(new File("img_bg_3stalacnite.png"));

            tile[9] = new code_Tile();
            tile[9].image = ImageIO.read(new File("img_bg_3wall.png"));

            tile[10] = new code_Tile();
            tile[10].image = ImageIO.read(new File("img_bg_3transition4.png"));

            tile[11] = new code_Tile();
            tile[11].image = ImageIO.read(new File("img_bg_4water.png"));

            tile[12] = new code_Tile();
            tile[12].image = ImageIO.read(new File("img_bg_4algae.png"));

            tile[13] = new code_Tile();
            tile[13].image = ImageIO.read(new File("img_bg_4wall.png"));

            tile[14] = new code_Tile();
            tile[14].image = ImageIO.read(new File("img_bg_4chest.png"));

            tile[15] = new code_Tile();
            tile[15].image = ImageIO.read(new File("img_bg_4transition5.png"));

            tile[16] = new code_Tile();
            tile[16].image = ImageIO.read(new File("img_bg_5floor.png"));

            tile[17] = new code_Tile();
            tile[17].image = ImageIO.read(new File("img_bg_5wall.png"));

            tile[18] = new code_Tile();
            tile[18].image = ImageIO.read(new File("img_bg_2flower.png"));

            tile[19] = new code_Tile();
            tile[19].image = ImageIO.read(new File("img_bg_1tree.png"));

            tile[20] = new code_Tile();
            tile[20].image = ImageIO.read(new File("img_bg_2bigflower.png"));

            tile[21] = new code_Tile();
            tile[21].image = ImageIO.read(new File("img_bg_5rug.png"));

        } catch (IOException e) {
            System.out.println("EXEPTION ");
            System.exit(0);
            e.printStackTrace();
        }
        ;

    }

    /**
     * This function changes the worldY, which is the relative y in which the map is rendered.
     * @param speed the speed that the map will move down
     */
    public void moveDown(double speed) {
        worldY += speed;
    }

    /**
     * This function changes the worldY, which is the relative y in which the map is rendered.
     * @param speed the speed that the map will move up
     */
    public void moveUp(double speed) {
        worldY -= speed;
    }

    /**
     * This function draws the map and the life gift on the screen.
     * @param g2 the 2d Graphics
     */
    public void draw(Graphics2D g2) {
        int worldRow = tileMap.length;
        int worldCol = tileMap[0].length;

        for (int i = worldRow - 1; i >= 0; i--) {
            for (int o = 0; o < worldCol; o++) {
                g2.drawImage(tile[tileMap[i][o]].image, code_Tile.size * o,
                    code_MainGame.SCREENHEIGHT - code_MainGame.TILESIZE * (230 - i) + worldY, null);
            }
        }
        if (!heartGiven) {
            lifeGift.draw(g2);
        }
    }
}
