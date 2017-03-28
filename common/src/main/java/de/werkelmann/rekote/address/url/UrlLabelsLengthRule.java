package de.werkelmann.rekote.address.url;

import de.werkelmann.rekote.address.ConnectionRule;

public class UrlLabelsLengthRule implements ConnectionRule {
    @Override
    public boolean isValid(String address) {
        for (String label : address.split(".")) {
            if (label.length() > 63) {
                return false;
            }
        }
        return true;
    }
}
