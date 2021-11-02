import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class RenderSinglePlayerMap {

    static JFrame frame = new JFrame("Survival Craft - Singleplayer"); // create the JFrame
    static JLabel player = new JLabel(new ImageIcon("src/main/resources/graphics/player.jpg")); // player object
    static JLayeredPane backgroundpanel = new JLayeredPane();
    static int maxtiles = 256;
    static int xtilecounter = 0;
    static int ytilecounter = 0;
    static int playerheight = 80;
    static int playerwidth = 80;

    static public void main () {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720); // set the width and height of the window
        frame.setIconImage(new ImageIcon("src/main/resources/graphics/player.jpg").getImage()); // set the window icon
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // window center screen
        JLayeredPane playerpanel = new JLayeredPane();
        playerpanel.setBounds(0,0, frame.getWidth(), frame.getHeight());
        playerpanel.add(player, JLayeredPane.POPUP_LAYER);

        // set the height and width of the player to the declared variables and set the player's position to the centre of the map
        player.setBounds(frame.getWidth()/2-playerwidth/2,frame.getHeight()/2-playerheight/2, playerwidth, playerheight);

        frame.add(playerpanel);
        frame.setVisible(true);
        System.out.println("Generating map..");
        generatemap();
        //update();
    }

    static void update () {
        // called once per frame
        // make asynchronous
        try {
            // update every 50 milliseconds
            Thread.sleep(50);
            update();
        }
        catch (Exception e) {
            CrashHandler.main(e.getMessage());
        }
    }

    static void generatemap () {

        if (maxtiles > 0) { // check if the renderer has reached the maximum number of tiles
            Random rand = new Random();
            int n = rand.nextInt(7); // random number between 0 and 7
            String texturesel = "";
            if (n == 1) {
                texturesel = "dirt";
            }
            else if (n == 2) {
                texturesel = "grass";
            }
            else if (n == 3) {
                texturesel = "log";
            }
            else if (n == 4) {
                texturesel = "leaves";
            }
            else if (n == 5) {
                texturesel = "water";
            }
            else if (n == 6) {
                texturesel = "stone";
            }
            else if (n == 7) {
                texturesel = "cobblestone";
            }
            else {
                texturesel = "stone";
            }

            JLabel tile = new JLabel(new ImageIcon("src/main/resources/graphics/"+texturesel+".jpg")); // set the texture

            if (xtilecounter < frame.getWidth() / 0.5) { // check if the X axis for this row is done yet
                tile.setBounds(xtilecounter+50, 0, 100, 100);
                backgroundpanel.add(tile, JLayeredPane.DEFAULT_LAYER);
                xtilecounter = xtilecounter + 50;
            }
            // known bug past this point
            else if (ytilecounter < frame.getHeight() / 0.5) { // check if the Y axis for this column is done yet
                tile.setBounds(0, ytilecounter+50, 100, 100);
                backgroundpanel.add(tile, JLayeredPane.DEFAULT_LAYER);
                ytilecounter = ytilecounter + 50;
            }
            maxtiles = maxtiles -1;
            generatemap();
        }
        else {
            // Map has been generated
            System.out.println("Map generated!");
            backgroundpanel.setBounds(0,0, frame.getWidth(), frame.getHeight());
            frame.add(backgroundpanel);
        }

    }

}
