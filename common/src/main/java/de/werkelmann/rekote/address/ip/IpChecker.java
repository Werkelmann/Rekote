package de.werkelmann.rekote.address.ip;

import de.werkelmann.rekote.address.NotEmptyRule;
import de.werkelmann.rekote.address.ServerConnectionChecker;

public class IpChecker extends ServerConnectionChecker {

    public IpChecker() {
        rules.add(new NotEmptyRule());
        rules.add(new IsIpRule());
    }
}
