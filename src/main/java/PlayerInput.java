import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class PlayerInput {

    private static volatile boolean wPressed = false;
    private static volatile boolean aPressed = false;
    private static volatile boolean sPressed = false;
    private static volatile boolean dPressed = false;

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
                            if (ke.getKeyCode() == KeyEvent.VK_W) {
                                wPressed = true;
                            }
                            // A
                            else if (ke.getKeyCode() == KeyEvent.VK_A) {
                                aPressed = true;
                            }
                            // S
                            else if (ke.getKeyCode() == KeyEvent.VK_S) {
                                sPressed = true;
                            }
                            // D
                            else if (ke.getKeyCode() == KeyEvent.VK_D) {
                                dPressed = true;
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
