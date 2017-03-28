package de.werkelmann.rekote.address.ip;

import de.werkelmann.rekote.address.ConnectionRule;
import org.apache.commons.validator.routines.InetAddressValidator;

public class IsIpRule implements ConnectionRule {
    @Override
    public boolean isValid(String address) {
        return InetAddressValidator.getInstance().isValid(address);
    }
}
