package de.werkelmann.rekote.address.url;

import de.werkelmann.rekote.address.ConnectionRule;

public class UrlValidCharRule implements ConnectionRule {
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
