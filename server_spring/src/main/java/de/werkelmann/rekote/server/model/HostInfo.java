package de.werkelmann.rekote.server.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostInfo {

    private final String hostName;
    private final String ipAddress;

    public static HostInfo getInfo() {
        try {
            String host = InetAddress.getLocalHost().getHostName();
            String ip = InetAddress.getLocalHost().getHostAddress();
            return new HostInfo(host, ip);
        } catch (UnknownHostException e) {
            return new HostInfo("Could not get Host", "Could not get IP");
        }

    }

    private HostInfo(String hostName, String ip) {
        this.hostName = hostName;
        this.ipAddress = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
