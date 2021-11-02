import javax.swing.*;
import java.awt.*;


public class RenderSinglePlayerMap {
    static JFrame frame = new JFrame("Survival Craft - Singleplayer"); // create the JFrame
    static JLabel player = new JLabel(new ImageIcon("src/main/src/graphics/player.jpg"));
    static public void main () {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720); // set the width and height of the window
        frame.setIconImage(new ImageIcon("src/main/src/graphics/player.jpg").getImage()); // set the window icon
        frame.setBackground(Color.white);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // window center screen

        player.setPreferredSize(new Dimension(50,50));
        frame.add(player);
        frame.setVisible(true);
        update();
    }

    static void update () {
        // called once per frame
        if (PlayerInput.isWPressed()) {
            // move upwards
            player.setLocation(player.getLocation().x,player.getLocation().y+1);
            System.out.println("W pressed!");
        }
        try {
            // update every 50 milliseconds
            Thread.sleep(50);
            update();
        }
        catch (Exception e) {
            CrashHandler.main(e.getMessage());
        }
    }

}
