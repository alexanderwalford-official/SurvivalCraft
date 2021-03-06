import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

public class PlayerInput {

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
                            if (ke.getKeyCode() == KeyEvent.VK_W && RenderSinglePlayerMap.player.getLocation().y > 75) {
                                RenderSinglePlayerMap.player.setLocation(RenderSinglePlayerMap.player.getLocation().x,RenderSinglePlayerMap.player.getLocation().y-movespeed);
                                if (wPressed) {
                                    Animations.playerwalk("back");
                                    wPressed = true;
                                }
                            }
                            // A
                            else if (ke.getKeyCode() == KeyEvent.VK_A && RenderSinglePlayerMap.player.getLocation().x > RenderSinglePlayerMap.playerwidth / 2) {
                                RenderSinglePlayerMap.player.setLocation(RenderSinglePlayerMap.player.getLocation().x-movespeed,RenderSinglePlayerMap.player.getLocation().y);
                                if (!aPressed) {
                                    Animations.playerwalk("left");
                                    aPressed= true;
                                }
                            }
                            // S
                            else if (ke.getKeyCode() == KeyEvent.VK_S && RenderSinglePlayerMap.player.getLocation().y < RenderSinglePlayerMap.frame.getHeight() - RenderSinglePlayerMap.playerheight * 1.5) { // stops the player object from going out of bounds
                                RenderSinglePlayerMap.player.setLocation(RenderSinglePlayerMap.player.getLocation().x,RenderSinglePlayerMap.player.getLocation().y+movespeed); // sets the position of the player object
                                if (!sPressed) {
                                    Animations.playerwalk("front");
                                    sPressed = true;
                                }
                            }
                            // D
                            else if (ke.getKeyCode() == KeyEvent.VK_D && RenderSinglePlayerMap.player.getLocation().x < RenderSinglePlayerMap.frame.getWidth() - RenderSinglePlayerMap.playerwidth * 1.5) {

                                RenderSinglePlayerMap.player.setLocation(RenderSinglePlayerMap.player.getLocation().x+movespeed,RenderSinglePlayerMap.player.getLocation().y);
                                if (!dPressed) {
                                    Animations.playerwalk("right");
                                    dPressed = true;
                                }
                            }
                            else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                RenderSinglePlayerMap.frame.dispose();
                                MainMenu.main("back");
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            // W
                            if (ke.getKeyCode() == KeyEvent.VK_W) {
                                wPressed = false;
                                URL url = PlayerInput.class.getResource("/graphics/player/idle/1.png");
                                Image image = new ImageIcon(url).getImage();
                                RenderSinglePlayerMap.player.setIcon(new ImageIcon(image));
                            }
                            // A
                            else if (ke.getKeyCode() == KeyEvent.VK_A) {
                                aPressed = false;
                                URL url = PlayerInput.class.getResource("/graphics/player/idle/1.png");
                                Image image = new ImageIcon(url).getImage();
                                RenderSinglePlayerMap.player.setIcon(new ImageIcon(image));
                            }
                            // S
                            else if (ke.getKeyCode() == KeyEvent.VK_S) {
                                sPressed = false;
                                URL url = PlayerInput.class.getResource("/graphics/player/idle/1.png");
                                Image image = new ImageIcon(url).getImage();
                                RenderSinglePlayerMap.player.setIcon(new ImageIcon(image));
                            }
                            // D
                            else if (ke.getKeyCode() == KeyEvent.VK_D) {
                                dPressed = false;
                                URL url = PlayerInput.class.getResource("/graphics/player/idle/1.png");
                                Image image = new ImageIcon(url).getImage();
                                RenderSinglePlayerMap.player.setIcon(new ImageIcon(image));
                            }
                            break;
                    }
                    return false;
                }
            }
        });
    }
}
