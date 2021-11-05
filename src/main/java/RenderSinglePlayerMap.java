import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;


public class RenderSinglePlayerMap {

    static JFrame frame = new JFrame("Survival Craft - Singleplayer"); // create the JFrame
    static JLabel player = new JLabel(new ImageIcon("src/main/resources/graphics/player.jpg")); // player object
    static JLayeredPane backgroundpanel = new JLayeredPane();
    static int playerheight = 80;
    static int playerwidth = 80;

    static int multiplier = 50;
    static int x = -multiplier;
    static int y = 0;

    static int maxrows;
    static int maxcolumns;

    static public void main() {
        //DrawGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720); // set the width and height of the window
        maxrows = frame.getHeight() / 50 * multiplier;
        maxcolumns = frame.getWidth() / 50 * multiplier;
        frame.setIconImage(new ImageIcon("src/main/resources/graphics/player.jpg").getImage()); // set the window icon
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // window center screen
        JLayeredPane playerpanel = new JLayeredPane();
        playerpanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        playerpanel.add(player, JLayeredPane.POPUP_LAYER);

        // set the height and width of the player to the declared variables and set the player's position to the centre of the map
        player.setBounds(frame.getWidth() / 2 - playerwidth / 2, frame.getHeight() / 2 - playerheight / 2, playerwidth, playerheight);

        frame.add(playerpanel);
        frame.setVisible(true);
        System.out.println("Generating map..");
        generatemap();
        //update();
    }

    static void update() {
        // called once per frame
        // make asynchronous
        try {
            // update every 50 milliseconds
            Thread.sleep(50);
            update();
        } catch (Exception e) {
            CrashHandler.main(e.getMessage());
        }
    }

    static void generatemap() {

        Random rand = new Random();
        int n = rand.nextInt(7); // random number between 0 and 7
        String texturesel = "";
        if (n == 1) {
            texturesel = "dirt";
        } else if (n == 2) {
            texturesel = "grass";
        } else if (n == 3) {
            texturesel = "log";
        } else if (n == 4) {
            texturesel = "leaves";
        } else if (n == 5) {
            texturesel = "water";
        } else if (n == 6) {
            texturesel = "stone";
        } else if (n == 7) {
            texturesel = "cobblestone";
        } else {
            texturesel = "stone";
        }

        JLabel tile = new JLabel(new ImageIcon("src/main/resources/graphics/" + texturesel + ".jpg")); // set the texture

        if (x == maxcolumns) {
            // end the generation
            System.out.println("Map generated!");
            backgroundpanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            frame.add(backgroundpanel);
        }
        else {
            // keep generating
            if (y == maxrows) {
                x = x + multiplier;
                y = 0;
            }
            else {
                tile.setBounds(x, y, 100, 100);
                backgroundpanel.add(tile, JLayeredPane.DEFAULT_LAYER);
                y = y + multiplier;
            }
            generatemap();
        }
    }

    public static void DrawGUI () {
        // here we can render our GUI
        JPanel GUI = new JPanel();
        GUI.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        JLabel topGUIbg = new JLabel(new ImageIcon("src/main/resources/graphics/topbarGUI.jpg")); // top bar
        System.out.println(frame.getWidth());
        topGUIbg.setPreferredSize(new Dimension(10000,40));
        GUI.setBackground(Color.black);
        GUI.add(topGUIbg, JLayeredPane.POPUP_LAYER);
        frame.add(GUI);
    }

}


