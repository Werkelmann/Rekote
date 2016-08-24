package de.werkelmann.rekote.server.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

import de.werkelmann.rekote.server.util.RekoteException;

public class HostInfo {

    private final String hostName;
    private final String ipAddress;

    public HostInfo() throws RekoteException {
        try {
            this.hostName = InetAddress.getLocalHost().getHostName();
            this.ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RekoteException("HostInfo could not get initialized");
        }
    }

    public String getHostName() {
        return hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
