import javax.swing.*;
import java.awt.*;

public class GameEnd {

    static JFrame frame = new JFrame("Game Over!"); // create the JFrame

    public static void main (int playerscore, String reason) {
        frame.setSize(300, 500);
        frame.setLocationRelativeTo(null); // window center screen
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src/main/resources/graphics/player.png").getImage()); // set the window icon
        JLabel title = new JLabel("The game has ended! ");
        if (reason == "enemy") {
            title.setText("You died!");
        }
        else if (reason == "time") {
            title.setText("You survived!");
        }
        JLabel scoretext = new JLabel(playerscore + " PTS");
        scoretext.setFont(new Font("Srif", Font.PLAIN, 12));
        title.setFont(new Font("Srif", Font.PLAIN, 18));
        frame.add(scoretext);
        frame.add(title);
        frame.setVisible(true);
    }

}
