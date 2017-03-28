package de.werkelmann.rekote.address;

import java.util.LinkedList;
import java.util.List;

public abstract class ServerConnectionChecker {

    protected List<ConnectionRule> rules = new LinkedList<>();

    public boolean check(String address) {
        for (ConnectionRule rule : rules) {
            if (!rule.isValid(address)) {
                return false;
            }
        }
        return true;
    }
}
