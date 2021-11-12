import javax.swing.*;
import java.util.Random;

public class EnemyAI {

    static JLabel player = RenderSinglePlayerMap.player;
    static JLayeredPane pane = RenderSinglePlayerMap.mainpane;
    static int mindist = 10; // set this to ensure a minimum distance from the player
    static int enemycounter = 0;
    static int enemymovespeed = 1; // enemy move speed
    static int[] enemyhealth = {100};

    public static void SpawnEmemies() {
        // this will be called every X seconds
        // randomise which enemy will spawn and where
        System.out.println("Started enemy generator..");
        SpawnRandom();
    }

    static void TryAttack(JLabel enemy) {
        // try to attack the player
        RenderSinglePlayerMap.playerhealth = RenderSinglePlayerMap.playerhealth - 5;
        if (RenderSinglePlayerMap.playerhealth < 1) {
            // player has died
            System.out.println("Player has died!");
        }
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

                if (xdist == mindist && ydist == mindist) {
                    // enemy can stop moving and should try to attack
                    TryAttack(enemy);
                }
                else {
                    // now move the enemy towards the player
                    // we need to check if the enemy's position is less or more than the player's position
                    if (enemyposx > playerposx) {
                        // x pos is greater than player pos x
                        if (enemyposy > playerposy) {
                            enemy.setLocation(enemyposx - enemymovespeed, enemyposy - enemymovespeed);
                        }
                        else {
                            // x pos is greater than player pos x
                            // y pos is less than player pos y
                            enemy.setLocation(enemyposx - enemymovespeed, enemyposy + enemymovespeed);
                        }
                    }
                    else {
                        // x pos is less than player pos x
                        if (enemyposy > playerposy) {
                            // x pos is less than player pos x
                            // y pos is greater than player pos y
                            enemy.setLocation(enemyposx + enemymovespeed, enemyposy - enemymovespeed);
                        }
                        else {
                            // x pos is less than pos x
                            // y pos is less than player pos y
                            enemy.setLocation(enemyposx + enemymovespeed, enemyposy + enemymovespeed);
                        }
                    }
                }

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
            RenderSinglePlayerMap.timertext.setText("Calculating..");
            Thread.sleep(rand.nextInt(20 * 1000)); // 20 secs max between spawning
            Thread renewthread = new Thread(() -> {
                System.out.println("Spawning enemy..");
                int n = rand.nextInt(4);
                int rndx = rand.nextInt(RenderSinglePlayerMap.frame.getWidth() + 50);
                int rndy = rand.nextInt(RenderSinglePlayerMap.frame.getHeight() + 50);
                JLabel enemy = new JLabel(new ImageIcon("src/main/resources/graphics/enemies/Enemy0" + n + "/attack01.png")); // set the target graphic based on the random value
                enemy.setBounds(rndx, rndy, 100, 100);
                pane.add(enemy, JLayeredPane.MODAL_LAYER);
                enemycounter++;
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
