package de.werkelmann.rekote.client.address.ip;

import android.os.AsyncTask;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import de.werkelmann.rekote.client.address.AddressRule;

public class IsIpRule implements AddressRule {
    @Override
    public boolean isValid(String address) {
        try {
            return new AsyncTask<String, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(String... params) {
                    try {
                        Object res = InetAddress.getByName(params[0]);
                        return res instanceof Inet4Address || res instanceof Inet6Address;
                    } catch (final UnknownHostException ex) {
                        return false;
                    }
                }
            }.execute(address).get();
        } catch (Exception e) {
            return false;
        }
    }
}
