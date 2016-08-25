package de.werkelmann.rekote.client;

import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;

import de.werkelmann.rekote.client.ip.IpChecker;
import de.werkelmann.rekote.client.tasks.GetInfoAsyncTask;
import de.werkelmann.rekote.client.url.UrlChecker;
import de.werkelmann.rekote.model.HostInfo;
import de.werkelmann.rekote.util.RekoteException;

public class RekoteHttpClient {

    private final static String PROTOCOL_PREFIX = "http://";
    private final static String PATH = "/rekote";
    private final static String PATH_INFO = PATH + "/info";

    private String hostAddress;
    private String port;

    public RekoteHttpClient(String urlAddress, String port) throws RekoteException {
        if (isValidAddress(urlAddress) && isValidPort(port)) {
            this.hostAddress = urlAddress;
            this.port = port;
            return;
        }
        throw new RekoteException("Invalid server address");
    }

    private boolean isValidAddress(String address) {
        return new UrlChecker().check(address) || new IpChecker().check(address);
    }

    private boolean isValidPort(String port) {
        try {
            Integer portNumber = Integer.parseInt(port);
            return 0 < portNumber && portNumber < 65536;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean shutdown() {
        //TODO shutdown
        return false;
    }

    public HostInfo getHostInfo() throws RekoteException {
        try {
            AsyncTask<URL, Void, HostInfo> getInfo = new GetInfoAsyncTask();
            return getInfo.execute(buildUrl(PATH_INFO)).get();
        } catch (Exception e) {
            throw new RekoteException("Could not get Info from server");
        }
    }

    public boolean stopShutdown() {
        //TODO stop shutdown
        return false;
    }

    private URL buildUrl(String pathSuffix) throws MalformedURLException {
        return new URL(PROTOCOL_PREFIX + hostAddress + ":" + port + pathSuffix);
    }
}
