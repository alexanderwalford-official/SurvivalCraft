import javax.swing.*;
import java.util.Arrays;

public class MapTriggers {
    // class responsible for detecting if the player is over a specific X and Y value
    // will then fire the relevant function dependent on position

    static int itempickuprange = 50;
    static JLabel notif = new JLabel(new ImageIcon("src/main/resources/graphics/GUI/broadswordnotif.png"));

    public static void checkposition() {
        // broadsword detection
        try {
            Thread.sleep(50);
            Thread renewthread = new Thread(() -> {
                // update every 100 milliseconds
                // check if the player should pick up the sword
                if (RenderSinglePlayerMap.player.getLocation().x < RenderSinglePlayerMap.broadswordlocation[0] - RenderSinglePlayerMap.broadword.getWidth() * 0.5
                        && RenderSinglePlayerMap.player.getLocation().x < RenderSinglePlayerMap.broadswordlocation[0] + RenderSinglePlayerMap.broadword.getWidth() * 0.5 &&
                        RenderSinglePlayerMap.player.getLocation().y < RenderSinglePlayerMap.broadswordlocation[1] - RenderSinglePlayerMap.broadword.getHeight() * 0.5 &&
                        RenderSinglePlayerMap.player.getLocation().y < RenderSinglePlayerMap.broadswordlocation[1] + RenderSinglePlayerMap.broadword.getHeight() * 0.5 &&
                        !RenderSinglePlayerMap.haspickedupsword) {
                    RenderSinglePlayerMap.haspickedupsword = true;
                    RenderSinglePlayerMap.broadword.setVisible(false);
                    // draw the broad sword pickup notification
                    notif.setBounds(RenderSinglePlayerMap.frame.getWidth() / 2,RenderSinglePlayerMap.frame.getHeight() / 2,400,200);
                    RenderSinglePlayerMap.mainpane.add(notif, JLayeredPane.DRAG_LAYER);
                    System.out.println("Player has picked up the broadsword! Waves can now start.");
                    try {
                        Thread.sleep(3000);
                        // hide the broadsword notification after 3 seconds
                        notif.setVisible(false);
                    }
                    catch (Exception b) {
                        System.out.println(b);
                    }
                }
                checkposition();
            });
            renewthread.start();
        }
        catch (Exception e) {
            CrashHandler.main(e.getMessage());
        }

    }
}
