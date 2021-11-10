import java.util.Arrays;

public class MapTriggers {
    // class responsible for detecting if the player is over a specific X and Y value
    // will then fire the relevant function dependent on position

    static int itempickuprange = 50;

    public static void checkposition() {
        // broadsword detection
        try {
            Thread.sleep(50);
            Thread renewthread = new Thread(() -> {
                // update every 100 milliseconds
                // I think this IF statement could be a new record for me in terms of length... I'm pretty sure that there's a better way to do this but I don't have much time left
                if (RenderSinglePlayerMap.player.getLocation().x < RenderSinglePlayerMap.broadswordlocation[0] - RenderSinglePlayerMap.broadword.getWidth() * 0.5 && RenderSinglePlayerMap.player.getLocation().x < RenderSinglePlayerMap.broadswordlocation[0] + RenderSinglePlayerMap.broadword.getWidth() * 0.5 && RenderSinglePlayerMap.player.getLocation().y < RenderSinglePlayerMap.broadswordlocation[1] - RenderSinglePlayerMap.broadword.getHeight() * 0.5 && RenderSinglePlayerMap.player.getLocation().y < RenderSinglePlayerMap.broadswordlocation[1] + RenderSinglePlayerMap.broadword.getHeight() * 0.5 && !RenderSinglePlayerMap.haspickedupsword) {
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
