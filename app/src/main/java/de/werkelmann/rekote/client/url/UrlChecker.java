package de.werkelmann.rekote.client.url;

import java.util.LinkedList;

import de.werkelmann.rekote.client.AddressChecker;

/**
 * checks for a valid url
 * @see <a href="https://en.wikipedia.org/wiki/Hostname">Wikipedia</a> (24.08.2016)
 */
public class UrlChecker extends AddressChecker {

    public UrlChecker() {
        rules = new LinkedList<>();

        rules.add(new UrlLengthRule());
        rules.add(new UrlValidCharRule());
        rules.add(new UrlLabelsLengthRule());
        rules.add(new UrlValidBeginningAndEndRule());
    }
}
