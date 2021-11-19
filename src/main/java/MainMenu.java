import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.jar.JarInputStream;
import java.util.Random;
import static javax.swing.JOptionPane.showMessageDialog;

public class MainMenu {

    // Alexander Walford 2021
    static JTextArea usernamebox = new JTextArea("RandomPlayer");

    static void main () {
        JFrame frame = new JFrame("Survival Craft"); // create the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720); // set the width and height of the window
        try {
            URL url = MainMenu.class.getResource("/graphics/GUI/gamelogo_square.png");
            Image image = new ImageIcon(url).getImage();
            frame.setIconImage(image);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        frame.setBackground(Color.white);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // window center screen

        MultiplayerDataHandler.SetTrustManager();

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

        // checks if client is connected to the server
        boolean isconnected = netIsAvailable();
        if (!isconnected) {
            System.out.println("SYS: Could not connect to server. Please try again later.");
            showMessageDialog(null, "SYS: Could not connect to server. Please try again later.");
            System.exit(1);
        }
        else {
            System.out.println("SYS: Server is connected.");
        }

        // Adding elements to the JFrame

        // top spacing
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 100));
        panel.setBackground(Color.white);
        frame.add(panel, BorderLayout.PAGE_START);

        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(200, 40));
        panel3.setBackground(Color.white);

        // game logo
        try {
            URL url = MainMenu.class.getResource("/graphics/GUI/gamelogo-long.png");
            Image image = new ImageIcon(url).getImage();
            JLabel gamelogo = new JLabel(new ImageIcon(image));
            gamelogo.setPreferredSize(new Dimension(1280,150));
            panel3.add(gamelogo, JLayeredPane.POPUP_LAYER);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // buttons
        JButton singleplayer = new JButton("Singleplayer");
        singleplayer.setPreferredSize(new Dimension(200, 100));
        singleplayer.setFocusPainted(false);
        singleplayer.setContentAreaFilled(false);

        singleplayer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // button pressed
                startsingleplayer();
                frame.dispose();
            }
        });

        panel3.add(singleplayer, JLayeredPane.POPUP_LAYER);

        JButton multiplayer = new JButton("Multiplayer");
        multiplayer.setPreferredSize(new Dimension(200, 100));
        multiplayer.setFocusPainted(false);
        multiplayer.setContentAreaFilled(false);

        multiplayer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // button pressed
                startmultiplayer();
                frame.dispose();
            }
        });

        panel3.add(multiplayer, JLayeredPane.POPUP_LAYER);

        JButton leaderboard = new JButton("Leader Board");
        leaderboard.setPreferredSize(new Dimension(200, 100));
        leaderboard.setFocusPainted(false);
        leaderboard.setContentAreaFilled(false);
        panel3.add(leaderboard, JLayeredPane.POPUP_LAYER);

        JButton settings = new JButton("Game Settings");
        settings.setPreferredSize(new Dimension(200, 100));
        settings.setFocusPainted(false);
        settings.setContentAreaFilled(false);
        panel3.add(settings, JLayeredPane.POPUP_LAYER);

        Random rand = new Random();
        int n = rand.nextInt(999);

        // player name box
        JLabel usernametitle = new JLabel("Your Username: ");
        usernametitle.setBorder(new EmptyBorder(50,0,0,0));
        usernametitle.setFont(new Font("Srif", Font.PLAIN, 18));
        usernamebox.setText("RandomPlayer" + n);
        usernamebox.setBorder(new EmptyBorder(50,0,0,0));
        usernamebox.setFont(new Font("Srif", Font.PLAIN, 18));
        usernamebox.setForeground(Color.gray);
        panel3.add(usernametitle, JLayeredPane.POPUP_LAYER);
        panel3.add(usernamebox, JLayeredPane.POPUP_LAYER);
        frame.add(panel3, BorderLayout.CENTER);

        JPanel panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(200, 40));
        panel4.setBackground(Color.white);
        frame.add(panel4, BorderLayout.LINE_START);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.white);
        panel2.setPreferredSize(new Dimension(200, 40));
        JLabel footertext = new JLabel("Version 0.1 ALPHA by Alexander Walford");
        footertext.setFont(new Font("Srif", Font.PLAIN, 20));
        panel2.add(footertext);
        frame.add(panel2, BorderLayout.PAGE_END);

        JPanel panel6= new JPanel();
        panel6.setBackground(Color.white);
        panel6.setPreferredSize(new Dimension(200, 40));
        frame.add(panel6, BorderLayout.LINE_END);

        frame.setVisible(true); // show the window

        // start the background music from the AudioEngine
        AudioEngine.main("04");
    }


    // start a single player session
    public static void startsingleplayer () {
        System.out.println("SYS: Single player game mode selected.");
        PlayerInput.main();
        RenderSinglePlayerMap.main(usernamebox.getText());
    }

    // send the player to the multiplayer screen
    public static void startmultiplayer () {
        System.out.println("SYS: Multi player game mode selected.");
        PlayerInput.main();
        MultiplayerJoinScreen.main();
    }

    public static boolean netIsAvailable() {
        try {
            final URL url = new URL("https://renovatesoftware.com/");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

}
