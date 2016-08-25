package de.werkelmann.rekote.client;

import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;

import de.werkelmann.rekote.client.address.ip.IpChecker;
import de.werkelmann.rekote.client.address.port.PortChecker;
import de.werkelmann.rekote.client.tasks.GetInfoAsyncTask;
import de.werkelmann.rekote.client.address.url.UrlChecker;
import de.werkelmann.rekote.model.HostInfo;
import de.werkelmann.rekote.util.RekoteException;

public class RekoteHttpClient {

    private final static String PROTOCOL_PREFIX = "http://";
    private final static String PATH = "/rekote";
    private final static String PATH_INFO = PATH + "/info";

    private String hostAddress;
    private String port;

    public RekoteHttpClient(String address, String port) throws RekoteException {
        if (isValidAddress(address) && isValidPort(port)) {
            this.hostAddress = address;
            this.port = port;
            return;
        }
        throw new RekoteException("Invalid server address");
    }

    private boolean isValidAddress(String address) {
        return new UrlChecker().check(address) || new IpChecker().check(address);
    }

    private boolean isValidPort(String port) {
        return new PortChecker().check(port);
    }

    public boolean shutdown() {
        return shutdownIn(0);
    }

    public HostInfo getHostInfo() throws RekoteException {
        HostInfo info;
        try {
            AsyncTask<URL, Void, HostInfo> getInfo = new GetInfoAsyncTask();
            info = getInfo.execute(buildUrl(PATH_INFO)).get();
        } catch (Exception e) {
            info = null;
        }
        if (info != null) {
            return info;
        }
        throw new RekoteException("Could not get Info from server");
    }

    public boolean stopShutdown() {
        //TODO stop shutdown
        return false;
    }

    private URL buildUrl(String pathSuffix) throws MalformedURLException {
        return new URL(PROTOCOL_PREFIX + hostAddress + ":" + port + pathSuffix);
    }

    public boolean shutdownIn(int minutes) {
        //todo shutdown request
        return false;
    }
}
