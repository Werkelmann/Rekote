package de.werkelmann.rekote.address;

public class NotEmptyRule implements AddressRule {
    @Override
    public boolean isValid(String address) {
        return !address.isEmpty();
    }
}
