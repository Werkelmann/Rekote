package de.werkelmann.rekote.client.address.port;

import de.werkelmann.rekote.client.address.AddressRule;

public class PortRule implements AddressRule {
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
