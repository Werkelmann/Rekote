package de.werkelmann.rekote.client.address.ip;

import org.apache.commons.validator.routines.InetAddressValidator;

import de.werkelmann.rekote.client.address.AddressRule;

public class IsIpRule implements AddressRule {
    @Override
    public boolean isValid(String address) {
        return InetAddressValidator.getInstance().isValid(address);
    }
}
