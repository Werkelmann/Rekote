package de.werkelmann.rekote.client.address.url;

import de.werkelmann.rekote.client.address.AddressRule;

public class UrlValidCharRule implements AddressRule {
    @Override
    public boolean isValid(String address) {
        for (char c : address.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isDigit(c) && !(c == '-') && !(c == '.')) {
                return false;
            }
        }
        return true;
    }
}
