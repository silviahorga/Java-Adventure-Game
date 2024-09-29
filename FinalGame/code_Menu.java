import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * the Starting menu of the game.
 */
public class code_Menu extends JFrame {
    code_Menu menu;

    /**
     * The constructor for the menu.
     */
    public code_Menu() {
        menu = this;
        setTitle("Game Menu");
        setSize(480, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);

        ImageIcon title = new ImageIcon("img_title.png");
        JLabel titleLabel = new JLabel();
        titleLabel.setIcon(title);
        titleLabel.setBounds(0, 48, 480, 315);;
        panel.add(titleLabel, new GridBagConstraints());

        ImageIcon buttonImage = new ImageIcon("play.png");

        JButton startButton = new JButton();
        startButton.setBounds(3 * 48, 480, 192, 96);

        startButton.setIcon(buttonImage);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameStart();
            }
        });
        panel.add(startButton);

        setContentPane(panel);

    }

    /**
     * This function displays the endScreen.
     */
    public void gameEnd() {
        code_EndScreen endScreen = new code_EndScreen(menu);
        setContentPane(endScreen);
        endScreen.requestFocus();
        revalidate();
        repaint();
    }

    /**
     * This function displays the game.
     */
    public void gameStart() {
        code_MainGame game = new code_MainGame(menu);
        setContentPane(game);
        game.requestFocus();
        revalidate();
        repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                code_Menu startMenu = new code_Menu();
                startMenu.setVisible(true);
            }
        });
    }
}
