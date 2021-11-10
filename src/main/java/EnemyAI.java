import javax.swing.*;
import java.util.Random;

public class EnemyAI {

    static JLabel player = RenderSinglePlayerMap.player;
    static JLayeredPane pane = RenderSinglePlayerMap.mainpane;
    static int mindist = 10; // set this to ensure a minimum distance from the player
    static int enemymovespeed = 1;

    public static void SpawnEmemies() {
        // this will be called every X seconds
        // randomise which enemy will spawn and where
        System.out.println("Started enemy generator..");
        SpawnRandom();
    }

    static void TryAttack(JLabel enemy) {
        // try to attack the player

    }

    static void MoveEnemy(JLabel enemy) {
        try {
            Thread.sleep(50);
            Thread renewthread = new Thread(() -> {
                // define intial object values
                int playerposx = player.getLocation().x;
                int playerposy = player.getLocation().y;
                int enemyposx = enemy.getLocation().x;
                int enemyposy = enemy.getLocation().y;

                // now we need to calculate the distance from the player
                int xdist = playerposx - enemyposx + mindist;
                int ydist = playerposy - enemyposy + mindist;

                if (xdist == 10 && ydist == 10) {
                    // enemy can stop moving and should try to attack
                    TryAttack(enemy);
                }

                // now move the enemy towards the player
                // IMP: Make sure that you check if the player's x and y values are negative or positive
                enemy.setLocation(enemyposx - xdist * enemymovespeed / 10, enemyposy - ydist * enemymovespeed / 10);
                MoveEnemy(enemy);
            });
            renewthread.start();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    static void SpawnRandom() {
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(20 * 1000)); // 20 secs max between spawning
            Thread renewthread = new Thread(() -> {
                System.out.println("Spawning enemy..");
                int n = rand.nextInt(4);
                int rndx = rand.nextInt(RenderSinglePlayerMap.frame.getWidth() + 50);
                int rndy = rand.nextInt(RenderSinglePlayerMap.frame.getHeight() + 50);
                JLabel enemy = new JLabel(new ImageIcon("src/main/resources/graphics/enemies/Enemy0" + n + "/attack01.png")); // set the target graphic based on the random value
                enemy.setBounds(rndx, rndy, 50, 50);
                pane.add(enemy, JLayeredPane.MODAL_LAYER);
                MoveEnemy(enemy);
                SpawnRandom();
            });
            renewthread.start();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
