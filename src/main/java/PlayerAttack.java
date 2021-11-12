import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerAttack implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // here we want to fire the attack animation
        // and deal damage to an enemy if they are within range
        if (RenderSinglePlayerMap.haspickedupsword) {
            RenderSinglePlayerMap.player.setIcon(new ImageIcon("src/main/resources/graphics/player/attack.gif"));
            RenderSinglePlayerMap.player.setBounds(RenderSinglePlayerMap.player.getLocation().x, RenderSinglePlayerMap.player.getLocation().y, 240, 160);
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
