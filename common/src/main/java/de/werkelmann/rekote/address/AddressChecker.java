package de.werkelmann.rekote.address;

import java.util.LinkedList;
import java.util.List;

public abstract class AddressChecker {

    protected List<AddressRule> rules = new LinkedList<>();

    public boolean check(String address) {
        for (AddressRule rule : rules) {
            if (!rule.isValid(address)) {
                return false;
            }
        }
        return true;
    }
}
