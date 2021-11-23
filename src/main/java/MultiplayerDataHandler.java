import javax.net.ssl.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Scanner;

public class MultiplayerDataHandler {

    // for my project, I've decided to go with a new endpoint I've developed on my company's server

    // Renovate Network API  - Multiplayer Endpoint
    // Script written by Alexander Walford

    // Java is awkward wth self-signed SSL certificates, so you will need to
    // call SetTrustManager() unless it's from an official signing identity
    // call SendData() to send the player's position to the server
    // call GetData(username) to get player details from the server
    // call HandleData() to set the player's position from the data
    // call SendBoardData() to send the player's score
    // call GetBoardData() to get a specific scoreboard

    // Custom SSL Trust Manager (ignores signing authority)
    public static void SetTrustManager () {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
            public X509Certificate[] getAcceptedIssuers(){return null;}
            public void checkClientTrusted(X509Certificate[] certs, String authType){}
            public void checkServerTrusted(X509Certificate[] certs, String authType){}
        }};

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            System.out.println("SYS: Installed new certificate trust manager.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Setting player data.
    public static String SetPlayer (String apikey, String username, String lobby, String position, String rotation) throws IOException {
        URL url = new URL("https://renovatesoftware.com/API/setplayerdata/");

        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "apikey="+apikey+"&playerid="+username+"&lobbyid="+lobby+"&position="+position+"&rotation="+rotation;

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        return http.getResponseMessage();
    }

    // Getting player data.
    // change to GET method
    public static String GetPlayer (String apikey, String username) throws IOException {
        URL url = new URL("https://renovatesoftware.com/API/getplayerdata/");

        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "apikey="+apikey+"&playerid="+ username;

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        return http.getResponseMessage();
    }

    // Getting the player list.
    public static String GetPlayerList (String apikey, String lobby) throws IOException {
        URL url = new URL("https://renovatesoftware.com/API/getlobby/");

        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "apikey="+apikey+"&lobbyid="+ lobby;

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        return http.getResponseMessage();
    }

    // Setting Player Score On Scoreboard & Returning Their Rating
    public static String SendBoardData(String apikey, String boardid, String playerid, String score) throws IOException {
        URL url = new URL("https://renovatesoftware.com/API/setscore/");

        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "apikey="+apikey+"&boardid="+ boardid +"&playerid=" + playerid + "&score=" + score;

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + ": " + http.getResponseMessage());
        http.disconnect();
        return http.getResponseMessage();
    }

    // Getting All Players & Their Score From The Scoreboard
    public static String GetBoardDataGET (String apikey, String boardid) throws IOException {
        //Instantiating the URL class
        URL url = new URL("https://renovatesoftware.com/API/GET/getscore/"+apikey+"/"+boardid);
        //Retrieving the contents of the specified page
        Scanner sc = new Scanner(url.openStream());
        //Instantiating the StringBuffer class to hold the result
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()) {
            sb.append(sc.next());
        }
        //Retrieving the String from the String Buffer object
        String result = sb.toString();
        //Removing the HTML tags
        result = result.replaceAll("<[^>]*>", "");
        return result;
    }

}
