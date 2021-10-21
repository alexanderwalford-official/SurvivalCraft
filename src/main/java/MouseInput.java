import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput {
    // WIP: implement into a seperte class? requires additional "implements"
    public void addMouseListener(MouseEvent e) {
        System.out.println("X = " + e.getX() + " Y = " + e.getY());
        // here we need to translate the X and Y co-ordinates of the mouse to the angle of the camera
    }
}
