import javax.swing.*;
import java.awt.*;

public class GameEnd {

    static JFrame frame = new JFrame("Game Over!"); // create the JFrame

    public static void main (int playerscore, String reason) {
        frame.setSize(300, 500);
        frame.setLocationRelativeTo(null); // window center screen
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src/main/resources/graphics/GUI/gamelogo_square.png").getImage()); // set the window icon
        JLabel title = new JLabel();
        if (reason == "enemy") {
            title.setText("You died! Your score was " + playerscore);
        }
        else if (reason == "time") {
            title.setText("You survived! Your score was " + playerscore);
        }
        title.setFont(new Font("Srif", Font.PLAIN, 18));
        frame.add(title);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to exit SurvivalCraft?", "Close Game?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

    }


}
