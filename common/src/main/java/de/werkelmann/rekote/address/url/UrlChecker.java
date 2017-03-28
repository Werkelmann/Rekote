package de.werkelmann.rekote.address.url;

import de.werkelmann.rekote.address.NotEmptyRule;
import de.werkelmann.rekote.address.ServerConnectionChecker;

/**
 * checks for a valid url
 * @see <a href="https://en.wikipedia.org/wiki/Hostname">Wikipedia</a> (24.08.2016)
 */
public class UrlChecker extends ServerConnectionChecker {

    public UrlChecker() {
        rules.add(new NotEmptyRule());
        rules.add(new UrlLengthRule());
        rules.add(new UrlValidCharRule());
        rules.add(new UrlLabelsLengthRule());
        rules.add(new UrlValidBeginningAndEndRule());
    }
}
