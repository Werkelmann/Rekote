package de.werkelmann.rekote.client.address.ip;

import de.werkelmann.rekote.client.address.AddressChecker;
import de.werkelmann.rekote.client.address.NotEmptyRule;

public class IpChecker extends AddressChecker {

    public IpChecker() {
        rules.add(new NotEmptyRule());
        rules.add(new IsIpRule());
    }
}
