import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles all the enemies1. It will eventually have all the functions.
 */
public class code_EnemyHandler {
    //ENEMY ARRAY
    public ArrayList<code_Enemy2> enemies2 = new ArrayList<code_Enemy2>();
    code_Player player;
    private code_MainGame game;

    /**
     * Constructor for the EnemyHandler.
     * @param game reference to the code_MainGame
     * @param targetPlayer reference to the target player
     */
    public code_EnemyHandler(code_MainGame game, code_Player targetPlayer) {
        this.game = game;
        player = targetPlayer;
    }


    /**
     * This function spawns all the enemies. It chooses a random tile from the region and 
     * places an Enemy there.
     */
    public void spawnEnemies(){
        // Different spawn quantity based on the tiles

        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            //int randomNum = rand.nextInt((max - min) + 1) + min;
            int randY = rand.nextInt((211 - 181) + 1) + 181;
            int randX = rand.nextInt((9 - 1)) + 1;

            code_Enemy2 enemy2 = new code_Enemy2(game.manager.getPosFromTile(randX, randY)[0], game.manager.getPosFromTile(randX, randY)[1],player, 2000, game.bh);
            enemies2.add(enemy2);  
        }
        for (int i = 0; i < 8; i++) {
            Random rand = new Random();
            int randY = rand.nextInt((180 - 140) + 1) + 140;
            int randX = rand.nextInt((9 - 1) ) +1;

            code_Enemy2 enemy2 = new code_Enemy2(game.manager.getPosFromTile(randX, randY)[0], game.manager.getPosFromTile(randX, randY)[1],player, 2000, game.bh);
            enemies2.add(enemy2);  
        }
        for (int i = 0; i < 12; i++) {
            Random rand = new Random();
            int randY = rand.nextInt((139 - 102) + 1) + 102;
            int randX = rand.nextInt((9 - 1) ) +1;

            code_Enemy2 enemy2 = new code_Enemy2(game.manager.getPosFromTile(randX, randY)[0], game.manager.getPosFromTile(randX, randY)[1],player, 2000, game.bh);
            enemies2.add(enemy2);  
        }
        for (int i = 0; i < 20; i++) {
            Random rand = new Random();
            int randY = rand.nextInt((100 - 41) + 1) + 41;
            int randX = rand.nextInt((9 - 1) ) + 1;

            code_Enemy2 enemy2 = new code_Enemy2(game.manager.getPosFromTile(randX, randY)[0], game.manager.getPosFromTile(randX, randY)[1],player, 2000, game.bh);
            enemies2.add(enemy2);  
        }
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int randY = rand.nextInt((40 - 15) + 1) + 15;
            int randX = rand.nextInt((9 - 1) ) + 1;

            code_Enemy2 enemy2 = new code_Enemy2(game.manager.getPosFromTile(randX, randY)[0], game.manager.getPosFromTile(randX, randY)[1],player, 2000, game.bh);
            enemies2.add(enemy2);  
        }
    }

    /**
     * This function removes the specified enemy from the array list.
     * @param enemy the enemy to be removed
     */
    public void removeEnemy(code_Enemy2 enemy) {
        enemies2.remove(enemy);
    }

    /**
     * This function calculates and returns an arraylist of all the enemies on screen.
     * @return an arraylist of all the enemies on screen.
     */
    public ArrayList<code_Enemy2> getEnemiesOnScreen() {
        ArrayList<code_Enemy2> newEnemies = new ArrayList<code_Enemy2>();
        for (int i = 0; i < enemies2.size(); i++) {
            code_Enemy2 enemy = enemies2.get(i);
            if (enemy.y > 0 || enemy. y < code_MainGame.SCREENHEIGHT) {
                newEnemies.add(enemy);
            }
        }
        return (newEnemies);
    }
    //when the map moves down, the enemies1 have to be affected aswell

    /**
     * When the map moves down, the enemies have to all move down. 
     * @param speed the speed that the map is moving down .
     */
    public void moveDown(double speed) {
        for (int i = 0; i < enemies2.size(); i++) {
            code_Enemy2 enemy = enemies2.get(i);
            enemy.y += speed;
        }
    }

    /**
     * When the map moves up, the enemies have to all move up. 
     * @param speed the speed that the map is moving up .
     */
    public void moveUp(double speed) {
        for (int i = 0; i < enemies2.size(); i++) {
            code_Enemy2 enemy = enemies2.get(i);
            enemy.y -= speed;
        }

    }

    /**
     * This draws all the enemies on the screen.
     * @param g2 the graphics
     */
    public void drawEnemies(Graphics2D g2) {
        for (int i = 0; i < enemies2.size(); i++) {
            code_Enemy2 enemy = enemies2.get(i);
            enemy.draw(g2);
        }
    }
}
