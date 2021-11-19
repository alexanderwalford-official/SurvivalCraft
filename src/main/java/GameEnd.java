import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Arrays;

public class GameEnd {

    static JFrame frame = new JFrame("Game Ended"); // create the JFrame
    static JLabel playerscorelist = new JLabel("<html>");
    static int counter = 0;

    public static void main (int playerscore, String reason) {
        JPanel panel = new JPanel();
        frame.setSize(300, 500);
        frame.setLocationRelativeTo(null); // window center screen
        frame.setResizable(false);
        URL url = RenderSinglePlayerMap.class.getResource("/graphics/GUI/gamelogo_square.png");
        Image image = new ImageIcon(url).getImage();
        frame.setIconImage(image);
        JLabel title = new JLabel();


        try {
            // calculate the player's ranking using the server
            String response = MultiplayerDataHandler.SendBoardData("550039706949", "SurvivalCraftPublic",RenderSinglePlayerMap.playerid.getText(), Integer.toString(playerscore));

            if (response.contains("OK")) {
                // saved the score without issues

                System.out.println("SYS: Data saved on server. Attempting to get scoreboard data..");

                String boarddata = MultiplayerDataHandler.GetBoardDataGET("550039706949","SurvivalCraftPublic");
                String[] rawdata = boarddata.split("SPLIT");


                String rawdata0 = rawdata[0].replace("[","");
                String rawdata1 = rawdata0.replace("]","");
                String[] players = rawdata1.split(",");

                String rawdata0b = rawdata[1].replace("[","");
                String rawdata1b = rawdata0b.replace("]","");
                String[] scores = rawdata1b.split(",");

                RenderPlayers(players,scores);

            }
            else {
                // error from server
                System.out.println("SYS: Error from server.");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        if (reason == "enemy") {
            title.setText("You died! Your score was " + playerscore);
        }
        else if (reason == "time") {
            title.setText("You survived! Your score was " + playerscore);
        }
        title.setFont(new Font("Srif", Font.PLAIN, 18));
        playerscorelist.setFont(new Font("Srif", Font.PLAIN, 14));
        playerscorelist.setBounds(0,0,frame.getWidth(),frame.getHeight() - 100);

        JButton menubutton = new JButton("Main Menu");
        menubutton.setBounds(0,0,200,100);

        menubutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu newmenu = new MainMenu();
                newmenu.main("back");
                RenderSinglePlayerMap.frame.dispose();
                frame.dispose();
            }
        });

        JButton playagain = new JButton("Play Again");
        playagain.setBounds(0,0,200,100);
        playagain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu newmenu = new MainMenu();
                newmenu.main("singleplayer");
                RenderSinglePlayerMap.frame.dispose();
                frame.dispose();
            }
        });

        JButton quit = new JButton("Quit");
        quit.setBounds(0,0,200,100);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });


        panel.add(menubutton);
        panel.add(playagain);
        panel.add(quit);
        panel.add(title);
        panel.add(playerscorelist);
        frame.add(panel);
        frame.setVisible(true);

        // implement scoreboard API from server

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit SurvivalCraft?", "Close Game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

    }

    static void RenderPlayers(String[] players, String[] scores) {
        if (counter != players.length) {
            if (players[counter].replace("'", "").equals(RenderSinglePlayerMap.playerid.getText())) {
                playerscorelist.setText(playerscorelist.getText() + "<p style=\"color: red\">You scored " + scores[counter] + "</p>");
            }
            playerscorelist.setText(playerscorelist.getText() + "<p>" + players[counter] + " scored " + scores[counter] + "</p>");
            counter++;
            RenderPlayers(players, scores);
        }
    }


}
