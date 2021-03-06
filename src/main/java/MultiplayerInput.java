import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class MultiplayerInput {

    // modify script to work with multiplayer API

    private static volatile boolean wPressed = false;
    private static volatile boolean aPressed = false;
    private static volatile boolean sPressed = false;
    private static volatile boolean dPressed = false;
    public static int movespeed = 4;

    public static boolean isWPressed() {
        synchronized (PlayerInput.class) {
            return wPressed;
        }
    }

    public static boolean isAPressed() {
        synchronized (PlayerInput.class) {
            return aPressed;
        }
    }

    public static boolean isSPressed() {
        synchronized (PlayerInput.class) {
            return sPressed;
        }
    }

    public static boolean isDPressed() {
        synchronized (PlayerInput.class) {
            return dPressed;
        }
    }

    public static void main() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (PlayerInput.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            // W
                            if (ke.getKeyCode() == KeyEvent.VK_W && RenderSinglePlayerMap.player.getLocation().y > 0) {
                                wPressed = true;
                                RenderSinglePlayerMap.player.setLocation(RenderSinglePlayerMap.player.getLocation().x,RenderSinglePlayerMap.player.getLocation().y-movespeed);
                            }
                            // A
                            else if (ke.getKeyCode() == KeyEvent.VK_A && RenderSinglePlayerMap.player.getLocation().x > 0) {
                                aPressed = true;
                                RenderSinglePlayerMap.player.setLocation(RenderSinglePlayerMap.player.getLocation().x-movespeed,RenderSinglePlayerMap.player.getLocation().y);
                            }
                            // S
                            else if (ke.getKeyCode() == KeyEvent.VK_S && RenderSinglePlayerMap.player.getLocation().y < RenderSinglePlayerMap.frame.getHeight() - RenderSinglePlayerMap.playerheight * 1.5) { // stops the player object from going out of bounds
                                sPressed = true;
                                RenderSinglePlayerMap.player.setLocation(RenderSinglePlayerMap.player.getLocation().x,RenderSinglePlayerMap.player.getLocation().y+movespeed); // sets the position of the player object
                            }
                            // D
                            else if (ke.getKeyCode() == KeyEvent.VK_D && RenderSinglePlayerMap.player.getLocation().x < RenderSinglePlayerMap.frame.getWidth() - RenderSinglePlayerMap.playerwidth * 1.5) {
                                dPressed = true;
                                RenderSinglePlayerMap.player.setLocation(RenderSinglePlayerMap.player.getLocation().x+movespeed,RenderSinglePlayerMap.player.getLocation().y);
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            // W
                            if (ke.getKeyCode() == KeyEvent.VK_W) {
                                wPressed = false;
                            }
                            // A
                            else if (ke.getKeyCode() == KeyEvent.VK_A) {
                                aPressed = false;
                            }
                            // S
                            else if (ke.getKeyCode() == KeyEvent.VK_S) {
                                sPressed = false;
                            }
                            // D
                            else if (ke.getKeyCode() == KeyEvent.VK_D) {
                                dPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            }
        });
    }
}
