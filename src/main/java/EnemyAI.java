import javax.swing.*;
import java.util.Random;

public class EnemyAI {

    static JLabel player = RenderSinglePlayerMap.player;
    static JLayeredPane pane = RenderSinglePlayerMap.mainpane;
    static int mindist = 40; // set this to ensure a minimum distance from the player
    static int enemycounter = 0;
    static int enemymovespeed = 1; // enemy move speed
    static int enemyattackdamage = 3;
    static boolean isinattackrange = false;
    // max stored enemies will be 10 for optimization
    static int[] enemyhealth = new int[10];
    static JLabel[] enemylist = new JLabel[50];
    static boolean[] enemyisinbounddata = new boolean[10];

    public static void SpawnEmemies() {
        // this will be called every X seconds
        // randomise which enemy will spawn and where
        System.out.println("SYS: Started enemy generator..");
        SpawnRandom();
    }

    static void TryAttack(JLabel enemy) {
        // try to attack the player
        try {
            // update every 500 milliseconds
            Thread.sleep(500);
            Thread renewthread = new Thread(() -> {
                RenderSinglePlayerMap.playerhealth = RenderSinglePlayerMap.playerhealth - enemyattackdamage;
                RenderSinglePlayerMap.healthtext.setText(RenderSinglePlayerMap.playerhealth + "/100 HP");
                if (RenderSinglePlayerMap.playerhealth < 1) {
                    // player has died
                    RenderSinglePlayerMap.GameOver = true;
                    GameEnd.main(RenderSinglePlayerMap.playerscore,"enemy");
                    RenderSinglePlayerMap.frame.setVisible(false);
                    RenderSinglePlayerMap.frame.dispose();

                }
            });
            if (!RenderSinglePlayerMap.GameOver) {
                renewthread.start();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    static void MoveEnemy(JLabel enemy, int enemyid) {
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
                    // ISSUES WITH THIS CODE:
                    // for some reason we're not updating the public objects for the player attack class to access it
                    // enemy can stop moving and should try to attack
                    isinattackrange = true;
                    enemyisinbounddata[enemyid] = true;
                    TryAttack(enemy);
                }
                else {
                    // now move the enemy towards the player
                    // we need to check if the enemy's position is less or more than the player's position
                    enemyisinbounddata[enemyid] = false;
                    isinattackrange = false;
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

                MoveEnemy(enemy, enemyid);
            });
            if (!RenderSinglePlayerMap.GameOver) {
                renewthread.start();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    static void SpawnRandom() {
        Random rand = new Random();
        try {
            RenderSinglePlayerMap.timertext.setText("Calculating..");
            // bug fix to prevent the game from waiting at the start
            if (enemycounter != 0) {
                Thread.sleep(rand.nextInt(20 * 1000)); // 20 secs max between spawning
            }
            Thread renewthread = new Thread(() -> {
                int n = rand.nextInt(4);
                int rndx = rand.nextInt(RenderSinglePlayerMap.frame.getWidth() + 50);
                int rndy = rand.nextInt(RenderSinglePlayerMap.frame.getHeight() + 50);
                JLabel enemy = new JLabel(new ImageIcon("src/main/resources/graphics/enemies/Enemy0" + n + "/attack01.png")); // set the target graphic based on the random value
                enemy.setBounds(rndx, rndy, 100, 100);
                pane.add(enemy, JLayeredPane.MODAL_LAYER);
                // create a new integer to save the current enemy ID before it gets incremented for the next enemy
                int enemyid = enemycounter;
                // add the enemy to the enemy list array and enemy health array
                enemyhealth[enemyid] = 100;
                enemylist[enemyid] = enemy;
                // increase the enemy counter
                enemycounter++;
                MoveEnemy(enemy, enemyid);
                SpawnRandom();
            });
            if (!RenderSinglePlayerMap.GameOver) {
                renewthread.start();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
