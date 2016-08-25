package de.werkelmann.rekote.client.address.url;

import de.werkelmann.rekote.client.address.AddressRule;

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
