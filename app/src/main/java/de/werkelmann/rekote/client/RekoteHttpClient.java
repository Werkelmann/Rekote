package de.werkelmann.rekote.client;

import java.net.HttpURLConnection;
import java.net.URL;

import de.werkelmann.rekote.client.ip.IpChecker;
import de.werkelmann.rekote.client.url.UrlChecker;
import de.werkelmann.rekote.model.HostInfo;
import de.werkelmann.rekote.util.RekoteException;

public class RekoteHttpClient {

    private final static String PATH = "/rekote";
    private final static String PATH_INFO = PATH + "/info";

    private String hostAddress;

    public RekoteHttpClient(String urlAddress) throws RekoteException {
        if (isValidAddress(urlAddress)) {
            this.hostAddress = urlAddress;
            return;
        }
        throw new RekoteException("Invalid server address");
    }

    private boolean isValidAddress(String address) {
        return new UrlChecker().check(address) || new IpChecker().check(address);
    }

    public boolean shutdown() {
        //TODO shutdown
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

    public boolean stopShutdown() {
        //TODO stop shutdown
        return false;
    }
}
