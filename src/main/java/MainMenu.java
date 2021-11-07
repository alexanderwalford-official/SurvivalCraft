import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarInputStream;
import java.util.Random;

public class MainMenu {

    // Alexander Walford 2021

    static void main () {
        JFrame frame = new JFrame("Survival Craft"); // create the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720); // set the width and height of the window
        frame.setIconImage(new ImageIcon("src/main/resources/graphics/gamelogo_square.png").getImage()); // set the window icon
        frame.setBackground(Color.white);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // window center screen

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
        JLabel gamelogo = new JLabel(new ImageIcon("src/main/resources/graphics/gamelogo-long.png"));
        gamelogo.setPreferredSize(new Dimension(1280,150));
        panel3.add(gamelogo, JLayeredPane.POPUP_LAYER);

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
                frame.setVisible(false);
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
                frame.setVisible(false);
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
        JTextArea usernamebox = new JTextArea("RandomPlayer" + n);
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
        AudioEngine audio = new AudioEngine();
        audio.HandleBackgroundMusic();
    }

    // start a single player session
    public static void startsingleplayer () {
        System.out.println("Single player button pressed..");
        PlayerInput.main();
        RenderSinglePlayerMap.main();
    }

    // send the player to the multiplayer screen
    public static void startmultiplayer () {
        System.out.println("Multi player button pressed..");
        PlayerInput.main();
        MultiplayerJoinScreen.main();
    }

}
