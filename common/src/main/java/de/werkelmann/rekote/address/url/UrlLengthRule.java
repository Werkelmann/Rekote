package de.werkelmann.rekote.address.url;

import de.werkelmann.rekote.address.AddressRule;

public class UrlLengthRule implements AddressRule {

    @Override
    public boolean isValid(String address) {
        return address.length() < 254;
    }
}
