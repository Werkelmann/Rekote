package de.werkelmann.rekote.client.address.ip;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import de.werkelmann.rekote.client.address.AddressRule;

public class IsIpRule implements AddressRule {
    @Override
    public boolean isValid(String address) {
        try {
            Object res = InetAddress.getByName(address);
            return res instanceof Inet4Address || res instanceof Inet6Address;
        } catch (final UnknownHostException ex) {
            return false;
        }
    }
}
