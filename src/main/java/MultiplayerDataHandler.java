import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class MultiplayerDataHandler {

    // for my project, I've decided to go with a new endpoint I've developed on my company's server

    // Renovate Network API  - Multiplayer Endpoint
    // Script written by Alexander Walford

    // the methods in this class can be called from anywhere in our project
    // call SendData() to send the player's position to the server
    // call GetData(username) to get player details from the server
    // call HandleData(HandleData) to set the player's position from the data

    public static void SendData () throws IOException {
        URL url = new URL("https://renovatesoftware.com/API/setplayerdata/");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // set the method to POST
        http.setDoOutput(true);

        Map<String,String> arguments = new HashMap<>();

        arguments.put("apikey", "550039706949"); // provide the API key
        arguments.put("playerid", "player3453"); // set the player id
        arguments.put("lobbyid", "lobby3534"); // set the lobby id
        arguments.put("position", "0,0,0"); // set the player position
        arguments.put("rotation", "0,0,0"); // set the player rotation

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            // handle output
            os.write(out);
        }
    }

    public static void GetData (String playername) {

    }

    public static void HandleData (String playername, String playerpos, String playerrot) {

    }

}
