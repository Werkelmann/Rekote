package de.werkelmann.rekote.client.url;

import de.werkelmann.rekote.client.AddressRule;

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
