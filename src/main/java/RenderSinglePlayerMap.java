import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;


public class RenderSinglePlayerMap {

    static JFrame frame = new JFrame("Survival Craft - Singleplayer"); // create the JFrame
    static JLabel player = new JLabel(new ImageIcon("src/main/resources/graphics/player.png")); // player object
    static JLayeredPane mainpane = new JLayeredPane();
    static int playerheight = 100;
    static int playerwidth = 50;

    static int cloudcounter = 0;
    static int maxcloudamount = 10; // set the max no. of clouds

    static int multiplier = 50;
    static int x = -multiplier;
    static int y = 0;

    static int maxrows;
    static int maxcolumns;

    static public void main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720); // set the width and height of the window
        frame.setBackground(Color.blue);
        maxrows = frame.getHeight() / 50 * multiplier;
        maxcolumns = frame.getWidth() / 50 * multiplier;
        frame.setIconImage(new ImageIcon("src/main/resources/graphics/player.png").getImage()); // set the window icon
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // window center screen
        mainpane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        mainpane.add(player, JLayeredPane.MODAL_LAYER);

        // set the height and width of the player to the declared variables and set the player's position to the centre of the map
        player.setBounds(frame.getWidth() / 2 - playerwidth / 2, frame.getHeight() / 2 - playerheight / 2, playerwidth, playerheight);

        generatemap();
        GenerateClouds();
        DrawGUI();
        frame.add(mainpane);
        frame.setVisible(true);
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

    static void GenerateClouds() {
        if (cloudcounter < maxcloudamount) {
            Random rand = new Random();
            int n = rand.nextInt(5); // random number between 0 and 5
            String texturesel = "";
            if (n == 1) {
                texturesel = "cloud1";
            } else if (n == 2) {
                texturesel = "cloud2";
            } else if (n == 3) {
                texturesel = "cloud3";
            } else if (n == 4) {
                texturesel = "cloud4";
            } else {
                texturesel = "cloud5";
            }

            JLabel tile = new JLabel(new ImageIcon("src/main/resources/graphics/clouds/" + texturesel + ".png")); // set the texture
            int cw = rand.nextInt(frame.getWidth());
            int ch = rand.nextInt(frame.getHeight());

            tile.setBounds(cw,ch,200,100);
            // now we need to randomise the locations of the clouds
            mainpane.add(tile, JLayeredPane.POPUP_LAYER);

            cloudcounter++;
            GenerateClouds();
        }
        else {
            System.out.println("Cloud generation completed!");
        }
    }

    static void generatemap() {

        // planned: add weight to each texture to determine how likely they are to spawn

        Random rand = new Random();
        int n = rand.nextInt(8); // random number between 0 and 7
        String texturesel = "";
        if (n == 1) {
            texturesel = "dirt.jpg";
        } else if (n == 2) {
            texturesel = "grass.jpg";
        } else if (n == 3) {
            texturesel = "log.jpg";
        } else if (n == 4) {
            texturesel = "leaves.jpg";
        } else if (n == 5) {
            texturesel = "water.png";
        } else if (n == 6) {
            texturesel = "stone.jpg";
        } else if (n == 7) {
            texturesel = "cobblestone.jpg";
        } else {
            texturesel = "stone.jpg";
        }

        JLabel tile = new JLabel(new ImageIcon("src/main/resources/graphics/" + texturesel)); // set the texture

        if (x == maxcolumns) {
            // end the generation
            System.out.println("Map generated!");
        }
        else {
            // keep generating
            if (y == maxrows) {
                x = x + multiplier;
                y = 0;
            }
            else {
                tile.setBounds(x, y, 100, 100);
                mainpane.add(tile, JLayeredPane.DEFAULT_LAYER);
                y = y + multiplier;
            }
            generatemap();
        }
    }

    static void DrawMapObjects() {
        // here we will render random objects into the map with triggers

    }

    static void DrawGUI() {
        // here we can render our GUI
        JLabel healthtext = new JLabel("100/100 HP");
        healthtext.setBounds(20,10,200,50);
        healthtext.setForeground(Color.white);
        healthtext.setFont(new Font("Srif", Font.PLAIN, 18));
        mainpane.add(healthtext, JLayeredPane.DRAG_LAYER);
        JLabel topGUIbg = new JLabel(new ImageIcon("src/main/resources/graphics/topbarGUI.png")); // top bar
        topGUIbg.setBounds(0,0,frame.getWidth(),70);
        mainpane.add(topGUIbg, JLayeredPane.DRAG_LAYER);
    }

}


