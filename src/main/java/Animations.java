import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Animations {
    static JLabel player = RenderSinglePlayerMap.player;
    static int[] idleframes = {1,2,3,4,5};
    static int[] attackframes = {0,1,2,3,4};

    public static void playeridle() {
        try {
            // update the player frames
            for (int i : idleframes) {
                URL url = Animations.class.getResource("/graphics/player/idle/" + i + ".png");
                Image image = new ImageIcon(url).getImage();
                player.setIcon(new ImageIcon(image));
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void playerattack() {
        try {
            // update the attacking textures
            player.setBounds(player.getLocation().x, player.getLocation().y,240,160);
            for (int i : idleframes) {
                URL url = Animations.class.getResource("/graphics/player/attack/" + i + ".png");
                Image image = new ImageIcon(url).getImage();
                player.setIcon(new ImageIcon(image));
                Thread.sleep(200);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
