package de.werkelmann.rekote.client.address;

public class NotEmptyRule implements AddressRule {
    @Override
    public boolean isValid(String address) {
        return !address.isEmpty();
    }
}
