package de.werkelmann.rekote.client.tasks;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallUrlTask extends AsyncTask<URL, Void, Boolean> {
    @Override
    protected Boolean doInBackground(URL... params) {
        try {
            URL url = params[0];
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();

            return (connection.getResponseCode() == 200);
        } catch (IOException e) {
            return false;
        }
    }
}
