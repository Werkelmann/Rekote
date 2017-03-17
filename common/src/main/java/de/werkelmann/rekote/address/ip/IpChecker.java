package de.werkelmann.rekote.address.ip;

import de.werkelmann.rekote.address.AddressChecker;
import de.werkelmann.rekote.address.NotEmptyRule;

public class IpChecker extends AddressChecker {

    public IpChecker() {
        rules.add(new NotEmptyRule());
        rules.add(new IsIpRule());
    }
}
