import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SettingsMenu {

    static JFrame frame = new JFrame();
    static JPanel panel = new JPanel();
    static String data;

    public static void main() throws IOException {
        // check if settings file exists
        getData();
        frame.setTitle("Survival Craft - Settings");
        frame.setSize(400, 200);
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // window center screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void getData() throws IOException {
        // read settings from file
        File f = new File("settings.pref");
        if(f.exists() && !f.isDirectory()) {
            // file exists
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        }
        else {
            // File does not exit, create it
            f.createNewFile();
        }

    }


}
