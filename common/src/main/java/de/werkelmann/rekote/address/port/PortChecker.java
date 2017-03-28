package de.werkelmann.rekote.address.port;

import de.werkelmann.rekote.address.ServerConnectionChecker;

public class PortChecker extends ServerConnectionChecker {

    public PortChecker() {
        rules.add(new PortRule());
    }
}
