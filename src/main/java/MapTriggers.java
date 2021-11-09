import java.util.Arrays;

public class MapTriggers {
    // class responsible for detecting if the player is over a specific X and Y value
    // will then fire the relevant function dependent on position

    static int itempickuprange = 5;

    public static void startchecking() {
        checkposition();
    }

    static void checkposition() {
        // broadsword detection
        try {
            Thread.sleep(100);
            Thread renewthread = new Thread(() -> {
                // update every 100 milliseconds
                    if (RenderSinglePlayerMap.player.getLocation().x < RenderSinglePlayerMap.broadswordlocation[0] - RenderSinglePlayerMap.broadword.getWidth() * 0.5 && RenderSinglePlayerMap.player.getLocation().x < RenderSinglePlayerMap.broadswordlocation[0] + RenderSinglePlayerMap.broadword.getWidth() * 0.5 && !RenderSinglePlayerMap.haspickedupsword) {
                        RenderSinglePlayerMap.haspickedupsword = true;
                        RenderSinglePlayerMap.broadword.setVisible(false);
                        System.out.println("Player has picked up the broadsword! Waves can now start.");
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
