import javax.swing.*;
import java.awt.*;

public class CrashHandler {
    static JFrame frame = new JFrame("Crash Handler"); // create the JFrame

    static public void main (String exception) {
        frame.setSize(300, 500);
        frame.setLocationRelativeTo(null); // window center screen
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src/main/resources/graphics/player.jpg").getImage()); // set the window icon
        JLabel title = new JLabel("An error was detected and caused the game to crash. To see what happened please read the error below: ");
        JLabel errortext = new JLabel(exception);
        errortext.setFont(new Font("Srif", Font.PLAIN, 12));
        title.setFont(new Font("Srif", Font.PLAIN, 18));
        frame.add(errortext);
        frame.add(title);
        frame.setVisible(true);
    }
}
