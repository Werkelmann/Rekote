package de.werkelmann.rekote.address.ip;

import de.werkelmann.rekote.address.AddressRule;
import org.apache.commons.validator.routines.InetAddressValidator;

public class IsIpRule implements AddressRule {
    @Override
    public boolean isValid(String address) {
        return InetAddressValidator.getInstance().isValid(address);
    }
}
