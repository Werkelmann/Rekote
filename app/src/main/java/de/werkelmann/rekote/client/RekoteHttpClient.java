package de.werkelmann.rekote.client;

import java.net.HttpURLConnection;
import java.net.URL;

import de.werkelmann.rekote.util.RekoteException;
import de.werkelmann.rekote.server.model.HostInfo;

public class RekoteHttpClient {

    private final static String PATH = "/rekote";
    private final static String PATH_INFO = PATH + "/info";

    private String hostAddress;

    public RekoteHttpClient(String urlAddress) throws RekoteException {
        if (isValidAddress(urlAddress)) {
            this.hostAddress = urlAddress;
            return;
        }
        throw new RekoteException("Invalid Url");
    }

    private boolean isValidAddress(String urlAddress) {
        //TODO check if valid url
        return false;
    }

    public boolean shutdown() {
        return false;
    }

    public HostInfo getHostInfo() {
        try {
            URL url = new URL(hostAddress + PATH_INFO);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //TODO get info from content
        } catch (java.io.IOException e) {
            e.printStackTrace(); //TODO try-catch in own class, error cannot happen because url must be valid
        }
        return null;
    }
}
