import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 * The main game class. This class holds the main game loop.
 */
public class code_MainGame extends JPanel implements KeyListener, ActionListener, MouseListener {
    // SCREEN
    public static final int TILESIZE = 48;
    public static final int SCREENCOLUMN = 10;
    public static final int SCREENROW = 16;
    public static final int SCREENWIDTH = TILESIZE * SCREENCOLUMN;
    public static final int SCREENHEIGHT = TILESIZE * SCREENROW;
    // TILES
    public code_TileManager manager = new code_TileManager();
    // PLAYER
    public code_Player player = new code_Player(this);
    public code_BulletHandler bh = new code_BulletHandler(this);
    // ENEMIES
    public code_EnemyHandler eh = new code_EnemyHandler(this, player);
    // NPC
    public code_NPC wizard = new code_NPC(this);
    // LEGENDARY HAT
    public code_LegendaryHat hat = new code_LegendaryHat(this);
    // INSTRUCTIONS
    public code_Instructions instructions = new code_Instructions(this);
    // TIMER
    public Timer timer;
    public static final int DELAY = 32; // 30 FPS

    public code_Menu menu;

    /** 
     * Constructor for the class.
    */
    public code_MainGame(code_Menu theMenu) {
        menu = theMenu;
        System.out.println("GAME STARTED");
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);
        this.setDoubleBuffered(true);
        timer = new Timer(DELAY, this);
        timer.start();
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        eh.spawnEnemies();
    }

    /**
     * Function to stop game.
     */
    public void stopGame() {
        timer.stop();
        menu.gameEnd();
    }

    /**
     * Move the whole map Up.
     * @param speed speed with which the map should move up.
     */
    public void moveDown(int speed) {
        manager.moveDown(speed);
        eh.moveDown(speed);
        bh.moveDown(speed);
    }

    /**
     * Move the whole map Down.
     * @param speed speed with which the map should move Down.
     */
    public void moveUp(int speed) {
        manager.moveUp(speed);
        eh.moveUp(speed);
        bh.moveUp(speed);
    }

    /**
     * Detect collisions with the bullets and the enemies.
     */
    public void detectCollisions() {
        Rectangle playerRect = player.getPlayerRect();

        for (int i = 0; i < bh.bullets.size(); i++) {
            code_Bullet bullet = bh.bullets.get(i);
            Rectangle bulletRect = new Rectangle((int) bullet.x, (int) bullet.y, 10, 10);

            // If the bullet comes from an enemy, check collision with the player.
            if (bullet.source == 1) { 
                if (playerRect.intersects(bulletRect)) {
                    player.life -= 1;
                    bh.bullets.remove(bullet);
                }
            }
            //detect the collision of only the enemies on-screen to reduce lag.
            ArrayList<code_Enemy2> enemies = eh.getEnemiesOnScreen();
            // If the bullet comes from the player, check collision with the enemies.
            if (bullet.source == 0) { 
                for (int o = 0; o < enemies.size(); o++) {
                    code_Enemy2 enemy = enemies.get(o);
                    Rectangle enemyRect = new Rectangle(enemy.x, enemy.y, 48, 48);
                    if (enemyRect.intersects(bulletRect)) {
                        enemy.life -= 1;
                        if (enemy.life == 0) {
                            enemy.stopTimer();
                            eh.removeEnemy(enemy);
                            enemies.remove(enemy);
                        }
                        bh.bullets.remove(bullet);
                    }
                }
            }
            if (Arrays.binarySearch(code_TileManager.wallNumList,
                    manager.getTileAt((int) bullet.x, (int) bullet.y)) >= 0) {
                bh.bullets.remove(bullet);
            }
        }
    }

    /**
     * ActionPerformed function for the timer. Each iteration, new collisions get detected, 
     * the player moves and the screen gets repainted.
     */
    public void actionPerformed(ActionEvent e) {
        detectCollisions();
        player.move();
        repaint();
    }

    /**
     * Paint all the components that need to be painted.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // PLAYER
        manager.draw(g2);
        // NPC
        wizard.dialogue(player, g2);
        wizard.draw(g2);
        // HAT
        hat.draw(g2);
        hat.gotHat(g2, player);
        // Instructions
        instructions.draw(g2);
        instructions.showInstructions(player, g2);
        player.draw(g2);
        eh.drawEnemies(g2);
        code_Heart.displayhearts(g2, player.life);

        bh.drawBullets(g2);

        g2.dispose();
    }

    // void displayHearts(Gra){

    // }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            player.left = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (keyCode == KeyEvent.VK_UP) {
            player.up = true;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            player.down = true;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            player.left = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (keyCode == KeyEvent.VK_UP) {
            player.up = false;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }

    /**
     * This function shoots a bullet when the mouse is clicked.
     */
    public void mousePressed(MouseEvent e) {
        int clickx = e.getX();
        int clicky = e.getY();
        player.shoot(clickx, clicky);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
