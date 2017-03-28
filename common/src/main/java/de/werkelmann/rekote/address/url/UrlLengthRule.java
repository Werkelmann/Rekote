package de.werkelmann.rekote.address.url;

import de.werkelmann.rekote.address.ConnectionRule;

public class UrlLengthRule implements ConnectionRule {

    @Override
    public boolean isValid(String address) {
        return address.length() < 254;
    }
}
