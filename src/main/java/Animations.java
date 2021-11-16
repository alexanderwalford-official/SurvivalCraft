import javax.swing.*;
import java.util.Random;

public class Animations {
    static JLabel player = RenderSinglePlayerMap.player;

    public static void playeridle() {
        try {
            // update the textures
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/idle/1.png"));
            Thread.sleep(200);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/idle/2.png"));
            Thread.sleep(200);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/idle/3.png"));
            Thread.sleep(200);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/idle/4.png"));
            Thread.sleep(200);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/idle/5.png"));
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void playerattack() {
        try {
            // update the textures
            player.setBounds(player.getLocation().x, player.getLocation().y,240,160);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/attack/0.png"));
            Thread.sleep(200);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/attack/1.png"));
            Thread.sleep(200);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/attack/2.png"));
            Thread.sleep(200);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/attack/3.png"));
            Thread.sleep(200);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/attack/4.png"));
            Thread.sleep(200);
            player.setIcon(new ImageIcon("src/main/resources/graphics/player/attack/0.png"));
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
