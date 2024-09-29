import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The object represents the ending screen of the game.
 */
public class code_EndScreen extends JPanel implements ActionListener {
    
    ImageIcon image;
    ImageIcon image2;
    JButton button = new JButton("PLAY AGAIN");
    code_Menu menu;
    /**
     * The end screen constructor.
     * The constructor adds a JLabel and a JButton to the panel.
     */
    public code_EndScreen(code_Menu menu) {
        System.out.println("EndScreen INIT");
        this.menu = menu;
        setLayout(null);

        image = new ImageIcon("img_menu_game_over1.png");
        JLabel label = new JLabel(image);
        label.setBounds(0, 0, 480, 514);
        
        image2 = new ImageIcon("img_playAgainButton.png");
        button.addActionListener(this);
        button.setBounds(144, 550, 170, 48);
        button.setIcon(image2);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(480, 768));

        add(label);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menu.gameStart();
    }
}
