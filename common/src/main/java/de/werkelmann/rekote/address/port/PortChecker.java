package de.werkelmann.rekote.address.port;

import de.werkelmann.rekote.address.AddressChecker;

public class PortChecker extends AddressChecker {

    public PortChecker() {
        rules.add(new PortRule());
    }
}
