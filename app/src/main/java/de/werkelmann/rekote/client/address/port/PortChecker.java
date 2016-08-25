package de.werkelmann.rekote.client.address.port;

import de.werkelmann.rekote.client.address.AddressChecker;

public class PortChecker extends AddressChecker {

    public PortChecker() {
        rules.add(new PortRule());
    }
}
