package de.werkelmann.rekote.address.url;

import de.werkelmann.rekote.address.ConnectionRule;

public class UrlValidBeginningAndEndRule implements ConnectionRule {
    @Override
    public boolean isValid(String address) {
        return !Character.isDigit(address.charAt(0)) && address.charAt(0) != '-' && address.charAt(address.length() - 1) != '-';
    }
}
