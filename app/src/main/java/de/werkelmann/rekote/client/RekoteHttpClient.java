package de.werkelmann.rekote.client;

import android.os.AsyncTask;
import de.werkelmann.rekote.address.ip.IpChecker;
import de.werkelmann.rekote.address.port.PortChecker;
import de.werkelmann.rekote.address.url.UrlChecker;
import de.werkelmann.rekote.client.tasks.GetInfoTask;
import de.werkelmann.rekote.client.tasks.ShutdownTask;
import de.werkelmann.rekote.model.HostInfo;
import de.werkelmann.rekote.util.RekoteException;

import java.net.MalformedURLException;
import java.net.URL;

public class RekoteHttpClient implements RekoteClient {

    private final static String PROTOCOL_PREFIX = "http://";
    private final static String PATH = "/rekote";
    private final static String PATH_INFO = PATH + "/info";
    private final static String PATH_SHUTDOWN_IN = PATH + "/shutdown/";
    private final static String PATH_SHUTDOWN_STOP = PATH_SHUTDOWN_IN + "stop";

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

    @Override
    public HostInfo getHostInfo() throws RekoteException {
        HostInfo info;
        try {
            AsyncTask<URL, Void, HostInfo> getInfo = new GetInfoTask();
            info = getInfo.execute(buildUrl(PATH_INFO)).get();
        } catch (Exception e) {
            info = null;
        }
        if (info != null) {
            return info;
        }
        throw new RekoteException("Could not get Info from server");
    }

    @Override
    public boolean stopShutdown() {
        try {
            AsyncTask<URL, Void, Boolean> stopShutdown = new ShutdownTask();
            stopShutdown.execute(buildUrl(PATH_SHUTDOWN_STOP));
            return stopShutdown.get();
        } catch (Exception e) {
            return false;
        }
    }

    private URL buildUrl(String pathSuffix) throws MalformedURLException {
        return new URL(PROTOCOL_PREFIX + hostAddress + ":" + port + pathSuffix);
    }

    @Override
    public boolean shutdownIn(int minutes) {
        try {
            AsyncTask<URL, Void, Boolean> stopShutdown = new ShutdownTask();
            stopShutdown.execute(buildUrl(PATH_SHUTDOWN_IN + minutes));
            return stopShutdown.get();
        } catch (Exception e) {
            return false;
        }
    }
}
