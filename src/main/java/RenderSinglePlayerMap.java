import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;


public class RenderSinglePlayerMap {

    static JFrame frame = new JFrame("Survival Craft - Singleplayer"); // create the JFrame

    static JLabel player;
    static JLabel broadword;

    static JLayeredPane mainpane = new JLayeredPane();
    static int playerheight = 122;
    static int playerwidth = 40;
    static int timeleft = 300; // in seconds, states how long the game will last
    static  int playerhealth = 100; // player health
    static JLabel healthtext = new JLabel("");
    static JLabel playerid = new JLabel("PLAYER_ID");
    static JLabel scoretext = new JLabel("0 PTS");
    static JLabel timertext = new JLabel();
    static int[] broadswordlocation = {0,0};
    static boolean haspickedupsword = false;
    static int playerscore = 0;
    static boolean GameOver = false;

    // if you want a texture to appear more frequently, just add it to the array more times
    static String[] texturelist = {"dirt","dirt","grass","grass","grass","grass","grass","grass","grass","grass","grass","stone","stone","stone","stone","stone","cobblestone","water","water","water","leaves","leaves","leaves","log"};

    static int cloudcounter = 0;
    static int maxcloudamount = 20; // set the max no. of clouds

    static int multiplier = 50;
    static int x = -multiplier;
    static int y = 0;

    static int maxrows;
    static int maxcolumns;

    static public void main(String playerid) {

        URL playerright = RenderSinglePlayerMap.class.getResource("/graphics/player/player_right.png");
        Image playerrightimg = new ImageIcon(playerright).getImage();
        player = new JLabel(new ImageIcon(playerrightimg)); // player object

        URL broadswordurl = RenderSinglePlayerMap.class.getResource("/graphics/items/broadsword.png");
        Image broadswordimage = new ImageIcon(broadswordurl).getImage();
        broadword = new JLabel(new ImageIcon(broadswordimage)); // broadsword


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720); // set the width and height of the window
        frame.setBackground(Color.blue);
        maxrows = frame.getHeight() / 50 * multiplier;
        maxcolumns = frame.getWidth() / 50 * multiplier;
        URL url = RenderSinglePlayerMap.class.getResource("/graphics/GUI/gamelogo_square.png");
        Image image = new ImageIcon(url).getImage();
        frame.setIconImage(image);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // window center screen
        mainpane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        mainpane.add(player, JLayeredPane.MODAL_LAYER);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to exit SurvivalCraft?", "Close Game?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

        // set the height and width of the player to the declared variables and set the player's position to the centre of the map
        player.setBounds(frame.getWidth() / 2 - playerwidth / 2, frame.getHeight() / 2 - playerheight / 2, playerwidth, playerheight);
        generatemap();
        GenerateClouds();
        DrawGUI(playerid);
        DrawMapObjects();
        frame.add(mainpane);
        frame.setVisible(true);
        // add the mouse listener from the player Attack class
        PlayerAttack ml = new PlayerAttack();
        frame.addMouseListener(ml);
        MapTriggers.checkposition();
        update();
        timer();
    }

    // for completing async loads
    static void update() {
        // called once per frame
        // make asynchronous
        try {
            // update every 100 milliseconds
            Thread.sleep(100);
            Thread renewthread = new Thread(() -> {
                // add methods that will run every 100 milliseconds here
                if (playerhealth > 0) {
                    healthtext.setText(playerhealth + "/100 HP");
                    scoretext.setText(playerscore + " PTS");
                }
                // player idle animation and checking player triggers
                try {
                    if (!PlayerInput.isWPressed() && !PlayerInput.isAPressed() && !PlayerInput.isSPressed() && !PlayerInput.isDPressed()) {
                        Thread.sleep(1000);
                        Animations.playeridle();
                    }
                    else {
                        Thread.sleep(1000);
                    }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                update();
            });
            if (!GameOver) {
                renewthread.start();
            }
        } catch (Exception e) {
            CrashHandler.main(e.getMessage());
        }
    }

    // for counting down the timer
    static void timer () {
        try {
            // update every 100 milliseconds
            Thread renewthread = new Thread(() -> {
                if (haspickedupsword) {
                    try {
                        Thread.sleep(1000);
                        timeleft--;
                        timertext.setText(timeleft + " sec");
                        if (timeleft == 295) {
                            // we can start to randomly spawn enemies
                            // after the 5 seconds peace period
                            EnemyAI.SpawnEmemies();
                        }
                        else if (timeleft == 0) {
                            // game over
                            GameOver = true;
                            GameEnd.main(playerscore, "time");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else  {
                    timertext.setText("Pick up sword to start!");
                }
                timer();
            });
            if (!GameOver) {
                renewthread.start();
            }
        } catch (Exception e) {
            CrashHandler.main(e.getMessage());
        }
    }

    static void GenerateClouds() {
        // add cloud movement animation?
        if (cloudcounter < maxcloudamount) {
            Random rand = new Random();
            int n = rand.nextInt(5); // random number between 0 and 5
            if (n == 0) {
                n++;
            }
            String texturesel = "cloud" + n;
            URL cloudurl = RenderSinglePlayerMap.class.getResource("graphics/clouds/" + texturesel + ".png");
            Image cloudtext = new ImageIcon(cloudurl).getImage();
            JLabel cloud = new JLabel();
            cloud.setIcon(new ImageIcon(cloudtext));
            int cw = rand.nextInt(frame.getWidth());
            int ch = rand.nextInt(frame.getHeight());
            cloud.setBounds(cw,ch,200,100);
            // now we need to randomise the locations of the clouds
            mainpane.add(cloud, JLayeredPane.POPUP_LAYER);

            cloudcounter++;
            GenerateClouds();
        }
        else {
            System.out.println("SYS: Cloud generation completed.");
        }
    }

    static void generatemap() {

        Random rand = new Random();
        int n = rand.nextInt(texturelist.length); // random number between 0 and 7
        String texturesel = texturelist[n];
        // implement the use if the texture weight array
        URL urltext = RenderSinglePlayerMap.class.getResource("graphics/textures/" + texturesel + ".png");
        Image tiletext = new ImageIcon(urltext).getImage();
        JLabel tile = new JLabel(new ImageIcon(tiletext)); // set the texture

        if (x == maxcolumns) {
            // end the generation
            System.out.println("SYS: Map generation complete.");
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
        Random rand = new Random();
        // first, let's start with the broadsword
        // try to spawn the broadsword away from the player randomly, but not too far away so that it can spawn outside the map
        broadswordlocation = new int[]{player.getLocation().x - rand.nextInt(frame.getWidth()) / 10, player.getLocation().y - rand.nextInt(frame.getHeight()) / 10};
        broadword.setBounds(broadswordlocation[0],broadswordlocation[1],20,80);
        mainpane.add(broadword, JLayeredPane.MODAL_LAYER);
    }

    static void DrawGUI(String splayerid) {
        // here we can render our GUI

        // player hp
        healthtext.setBounds(20,10,200,50);
        healthtext.setForeground(Color.white);
        healthtext.setFont(new Font("Srif", Font.PLAIN, 18));
        mainpane.add(healthtext, JLayeredPane.DRAG_LAYER);

        // player ID
        playerid.setText(splayerid);
        playerid.setBounds(frame.getWidth() - 180,10,200,50);
        playerid.setForeground(Color.white);
        playerid.setFont(new Font("Srif", Font.PLAIN, 18));
        mainpane.add(playerid, JLayeredPane.DRAG_LAYER);

        // score
        scoretext.setBounds(frame.getWidth() / 2 - 80,10,200,50);
        scoretext.setForeground(Color.white);
        scoretext.setFont(new Font("Srif", Font.PLAIN, 18));
        mainpane.add(scoretext, JLayeredPane.DRAG_LAYER);

        // timer text
        timertext.setBounds(frame.getWidth() / 2 + 80,10,200,50);
        timertext.setForeground(Color.white);
        timertext.setFont(new Font("Srif", Font.PLAIN, 18));
        mainpane.add(timertext, JLayeredPane.DRAG_LAYER);

        JLabel healthtext = new JLabel("");
        healthtext.setBounds(20,10,200,50);
        healthtext.setForeground(Color.white);
        healthtext.setFont(new Font("Srif", Font.PLAIN, 18));
        mainpane.add(healthtext, JLayeredPane.DRAG_LAYER);

        URL url = MainMenu.class.getResource("/graphics/GUI/topbarGUI.png");
        Image image = new ImageIcon(url).getImage();
        JLabel topGUIbg = new JLabel(new ImageIcon(image));
        topGUIbg.setBounds(0,0,frame.getWidth(),70);
        mainpane.add(topGUIbg, JLayeredPane.DRAG_LAYER);
    }


}


