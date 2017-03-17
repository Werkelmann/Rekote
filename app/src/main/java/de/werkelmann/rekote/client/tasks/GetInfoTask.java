package de.werkelmann.rekote.client.tasks;

import android.os.AsyncTask;
import de.werkelmann.rekote.model.HostInfo;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetInfoTask extends AsyncTask<URL, Void, HostInfo> {

    @Override
    protected HostInfo doInBackground(URL... params) {
        HttpURLConnection connection;
        try {
            URL url = params[0];
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                JSONObject message = readFromConnection(connection);
                return new HostInfo(message);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private JSONObject readFromConnection(HttpURLConnection connection) throws IOException, JSONException {
        String json = readStringFromConnection(connection);
        return new JSONObject(json);
    }

    private String readStringFromConnection(HttpURLConnection connection) throws IOException {
        StringBuilder builder = new StringBuilder();

        InputStreamReader in = new InputStreamReader((InputStream) connection.getContent());
        BufferedReader buff = new BufferedReader(in);

        String line = buff.readLine();
        while (line != null) {
            builder.append(line);
            line = buff.readLine();
        }

        return builder.toString();
    }

}
