package de.werkelmann.rekote.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

import de.werkelmann.rekote.util.RekoteException;

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

    public HostInfo(JSONObject message) throws RekoteException {
        try {
            this.hostName = message.getString("hostName");
            this.ipAddress = message.getString("ipAddress");
        } catch (JSONException e) {
            throw new RekoteException("HostInfo could not get initialized");
        }
    }

    public String getHostName() {
        return hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return hostName + " " + ipAddress;
    }
}
