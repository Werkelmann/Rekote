package de.werkelmann.rekote.client.address.url;

import de.werkelmann.rekote.client.address.AddressChecker;
import de.werkelmann.rekote.client.address.NotEmptyRule;

/**
 * checks for a valid url
 * @see <a href="https://en.wikipedia.org/wiki/Hostname">Wikipedia</a> (24.08.2016)
 */
public class UrlChecker extends AddressChecker {

    public UrlChecker() {
        rules.add(new NotEmptyRule());
        rules.add(new UrlLengthRule());
        rules.add(new UrlValidCharRule());
        rules.add(new UrlLabelsLengthRule());
        rules.add(new UrlValidBeginningAndEndRule());
    }
}
