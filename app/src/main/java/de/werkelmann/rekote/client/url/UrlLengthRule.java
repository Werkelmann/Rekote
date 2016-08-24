package de.werkelmann.rekote.client.url;

import de.werkelmann.rekote.client.AddressRule;

public class UrlLengthRule implements AddressRule {

    @Override
    public boolean isValid(String address) {
        return address.length() < 254;
    }
}
