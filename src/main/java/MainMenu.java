import javax.swing.*;
import java.awt.*;

public class MainMenu {
    static void main () {
        update();
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720); // set the width and height of the window
        frame.setTitle("Survival Craft"); // set the window title
        frame.setIconImage(new ImageIcon("src/main/src/player.jpg").getImage()); // set the window icon

        frame.setLocationRelativeTo(null); // window center screen

        JButton button = new JButton("Button 1 (PAGE_START)");
        frame.add(button, BorderLayout.PAGE_START);

        button = new JButton("Button 2 (CENTER)");
        button.setPreferredSize(new Dimension(200, 100));
        frame.add(button, BorderLayout.CENTER);

        button = new JButton("Button 3 (LINE_START)");
        frame.add(button, BorderLayout.LINE_START);

        button = new JButton("Long-Named Button 4 (PAGE_END)");
        frame.add(button, BorderLayout.PAGE_END);

        button = new JButton("5 (LINE_END)");
        frame.add(button, BorderLayout.LINE_END);

        frame.setVisible(true); // show the window
    }

    public static void update () {
        // called once per frame
    }

}
