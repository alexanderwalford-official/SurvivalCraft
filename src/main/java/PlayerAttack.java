import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerAttack implements MouseListener {

    static int rangecounter = 0;

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // here we want to fire the attack animation
        // and deal damage to an enemy if they are within range
        if (RenderSinglePlayerMap.haspickedupsword && EnemyAI.isinattackrange) {
            // will fire if the broadsword has been picked up and if the player is within attacking range
            RenderSinglePlayerMap.player.setIcon(new ImageIcon("src/main/resources/graphics/player/attack.gif"));
            RenderSinglePlayerMap.player.setBounds(RenderSinglePlayerMap.player.getLocation().x, RenderSinglePlayerMap.player.getLocation().y, 240, 160);
            // now check which enemies are within range
            attackenemiesinrange();
        }
    }

    // method is responsible for dealing damage to enemies within range of the player
    void attackenemiesinrange () {
        JLabel enemylist[] = EnemyAI.enemylist;
        boolean enemyrangelist[] = EnemyAI.enemyisinbounddata;
        int enemycount = EnemyAI.enemycounter;

        if (rangecounter != enemycount) {
            // only runs if has not completed iteration
            if (enemyrangelist[rangecounter] == true) {
                // enemy is in attack range, deal damage to it if not dead
                if (EnemyAI.enemyhealth[rangecounter] > 0) {
                    EnemyAI.enemyhealth[rangecounter] = EnemyAI.enemyhealth[rangecounter] - 50;
                }
                else {
                    // remove the enemy as it has died
                    // then give the player points
                    enemylist[rangecounter].setVisible(false);
                    RenderSinglePlayerMap.playerscore = RenderSinglePlayerMap.playerscore + 10;
                }
            }
            rangecounter++;
            attackenemiesinrange();
        }
        else {

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
