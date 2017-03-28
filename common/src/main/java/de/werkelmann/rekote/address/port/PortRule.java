package de.werkelmann.rekote.address.port;

import de.werkelmann.rekote.address.ConnectionRule;

public class PortRule implements ConnectionRule {
    @Override
    public boolean isValid(String address) {
        try {
            Integer portNumber = Integer.parseInt(address);
            return 0 < portNumber && portNumber < 65536;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
