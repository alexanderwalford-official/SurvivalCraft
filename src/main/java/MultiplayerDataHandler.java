import javax.net.ssl.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Dictionary;
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

    public static void SetTrustManager () {
        // java is awkward wth self-signed certificates, so I need to create my own trust manager that accepts all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
            public X509Certificate[] getAcceptedIssuers(){return null;}
            public void checkClientTrusted(X509Certificate[] certs, String authType){}
            public void checkServerTrusted(X509Certificate[] certs, String authType){}
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            System.out.println("SYS: Installed new certificate trust manager.");
        } catch (Exception e) {
            ;
        }
    }

    public static byte[] SetPlayer (String apikey, String username, String lobby, String position, String rotation) throws IOException {
        URL url = new URL("https://renovatesoftware.com/API/setplayerdata/");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // set the method to POST
        http.setDoOutput(true);

        Map<String,String> arguments = new HashMap<>();

        arguments.put("apikey", apikey); // provide the API key
        arguments.put("playerid", username); // set the player id
        arguments.put("lobbyid", lobby); // set the lobby id
        arguments.put("position", position); // set the player position
        arguments.put("rotation", rotation); // set the player rotation

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
            return out;
        }
    }

    public static byte[] GetPlayer (String apikey, String username) throws IOException {
        URL url = new URL("https://renovatesoftware.com/API/getplayerdata/");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // set the method to POST
        http.setDoOutput(true);

        Map<String,String> arguments = new HashMap<>();

        arguments.put("apikey", apikey); // provide the API key
        arguments.put("playerid", username); // set the player id

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
            return out;
        }
    }

    public static byte[] GetPlayerList (String apikey, String lobby) throws IOException {
        URL url = new URL("https://renovatesoftware.com/API/getplayerlist/");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // set the method to POST
        http.setDoOutput(true);

        Map<String,String> arguments = new HashMap<>();

        arguments.put("apikey", apikey); // provide the API key
        arguments.put("lobbyid", lobby); // set the lobby id

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
            return out;
        }
    }

    public static void SendBoardData(String apikey, String boardid, String playerid, String score) throws IOException {

        URL url = new URL("https://renovatesoftware.com/API/setscore/");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "apikey="+apikey+"&boardid="+ boardid +"&playerid=" + playerid + "&score=" + score;

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

}
