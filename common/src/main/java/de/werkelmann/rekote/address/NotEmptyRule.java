package de.werkelmann.rekote.address;

public class NotEmptyRule implements ConnectionRule {
    @Override
    public boolean isValid(String address) {
        return !address.isEmpty();
    }
}
