import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;

public class MapTriggers {
    // class responsible for detecting if the player is over a specific X and Y value
    // will then fire the relevant function dependent on position

    static int itempickuprange = 80;

    static JLabel notif;

    public static void checkposition() {
        URL notifimgurl = RenderSinglePlayerMap.class.getResource("/graphics/GUI/broadswordnotif.png");
        Image notifimg = new ImageIcon(notifimgurl).getImage();
        notif =  new JLabel(new ImageIcon(notifimg));
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
                    notif.setBounds(RenderSinglePlayerMap.frame.getWidth() / 2 - 400,RenderSinglePlayerMap.frame.getHeight() / 2 - 200,400,200);
                    RenderSinglePlayerMap.mainpane.add(notif, JLayeredPane.DRAG_LAYER);
                    System.out.println("SYS: Player has picked up the broadsword! Waves can now start.");
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
