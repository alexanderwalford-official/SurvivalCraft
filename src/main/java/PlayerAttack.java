import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerAttack implements MouseListener {

    static int rangecounter = 0;

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // here we want to fire the attack animation
        // and deal damage to an enemy if they are within range
        if (RenderSinglePlayerMap.haspickedupsword) {
            // will fire if the broadsword has been picked up and if the player is within attacking range
            try {
                Thread renewthread = new Thread(() -> {
                    // animate the player attacking
                    Animations.playerattack();
                    // now check which enemies are within range
                    attackenemiesinrange();
                });
                renewthread.start();
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // method is responsible for dealing damage to enemies within range of the player
    void attackenemiesinrange () {
        try {
            // new thread for attacking enemies
            Thread attackthread = new Thread(() -> {
                JLabel[] enemylist = EnemyAI.enemylist;
                boolean[] enemyrangelist = EnemyAI.enemyisinbounddata;
                int enemycount = EnemyAI.enemycounter;
                int[] enemieshealth = EnemyAI.enemyhealth;

                if (rangecounter != enemycount) {
                    // only runs if code has not completed iteration
                    // temporarily removed range functionality due to a bug
                    //if (enemyrangelist[rangecounter]) {
                        // enemy is in attack range, deal damage to it if not dead
                        if (enemieshealth[rangecounter] > 0) {
                            // health is greater than 0
                            enemieshealth[rangecounter] = EnemyAI.enemyhealth[rangecounter] - 50;
                            RenderSinglePlayerMap.playerscore = RenderSinglePlayerMap.playerscore + 5; // add 5 to score
                        } else {
                            // remove the enemy as it has died
                            // then give the player points
                            enemylist[rangecounter].setVisible(false); // set the game object to being invisible
                            RenderSinglePlayerMap.playerscore = RenderSinglePlayerMap.playerscore + 10; // add 10 to score
                        }
                    //} else {
                        //System.out.println("Enemy is not in range");
                    //}
                    // iterate again
                    rangecounter++;
                    attackenemiesinrange();
                }
            });
            attackthread.start();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) { }

    @Override
    public void mouseExited(MouseEvent arg0) { }

    @Override
    public void mousePressed(MouseEvent arg0) { }

    @Override
    public void mouseReleased(MouseEvent arg0) { }
}
