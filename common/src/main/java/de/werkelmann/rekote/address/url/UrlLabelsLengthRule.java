package de.werkelmann.rekote.address.url;

import de.werkelmann.rekote.address.AddressRule;

public class UrlLabelsLengthRule implements AddressRule {
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
