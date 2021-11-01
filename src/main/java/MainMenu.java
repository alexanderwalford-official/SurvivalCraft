import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    // Alexander Walford 2021

    static void main () {
        update();
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720); // set the width and height of the window
        frame.setTitle("Survival Craft"); // set the window title
        frame.setIconImage(new ImageIcon("src/main/src/graphics/player.jpg").getImage()); // set the window icon
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
        JLabel gamelogo = new JLabel(new ImageIcon("src/main/src/graphics/gamelogo-long.jpg"));
        gamelogo.setPreferredSize(new Dimension(1280,150));
        panel3.add(gamelogo);

        // buttons
        JButton singleplayer = new JButton("Single Player");
        singleplayer.setPreferredSize(new Dimension(200, 100));
        singleplayer.setFocusPainted(false);
        singleplayer.setContentAreaFilled(false);

        singleplayer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // button pressed
                startsingleplayer();
                //frame.hide();
            }
        });

        panel3.add(singleplayer);

        JButton multiplayer = new JButton("Multi Player");
        multiplayer.setPreferredSize(new Dimension(200, 100));
        multiplayer.setFocusPainted(false);
        multiplayer.setContentAreaFilled(false);
        panel3.add(multiplayer);

        JButton leaderboard = new JButton("Leader Board");
        leaderboard.setPreferredSize(new Dimension(200, 100));
        leaderboard.setFocusPainted(false);
        leaderboard.setContentAreaFilled(false);
        panel3.add(leaderboard);

        JButton settings = new JButton("Game Settings");
        settings.setPreferredSize(new Dimension(200, 100));
        settings.setFocusPainted(false);
        settings.setContentAreaFilled(false);
        panel3.add(settings);

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

    // probably not going to implement in main menu
    public static void update () {
        // called once per frame

    }

    // start a single player session
    public static void startsingleplayer () {
        PlayerInput.main();
        RenderSinglePlayerMap.main();
    }

}
