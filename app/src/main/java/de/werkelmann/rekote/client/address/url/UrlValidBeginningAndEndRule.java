package de.werkelmann.rekote.client.address.url;

import de.werkelmann.rekote.client.address.AddressRule;

public class UrlValidBeginningAndEndRule implements AddressRule {
    @Override
    public boolean isValid(String address) {
        return !address.isEmpty() && !Character.isDigit(address.charAt(0)) && address.charAt(0) != '-' && address.charAt(address.length() - 1) != '-';
    }
}
