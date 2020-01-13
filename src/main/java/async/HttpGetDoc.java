package async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

public class HttpGetDoc implements Runnable {

    private final LinkedBlockingQueue queue;

    public HttpGetDoc(LinkedBlockingQueue queue) {

        this.queue = queue;
    }

    private String getResponse(String url) {

        URL obj = null;
        HttpURLConnection con = null;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder response = null;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @Override
    public void run() {
        try {
            String data = (String) queue.take();
            String response = getResponse(data);
            System.out.println(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
