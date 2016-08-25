package de.werkelmann.rekote.client.address.url;

import de.werkelmann.rekote.client.address.AddressRule;

public class UrlLengthRule implements AddressRule {

    @Override
    public boolean isValid(String address) {
        return address.length() < 254;
    }
}
