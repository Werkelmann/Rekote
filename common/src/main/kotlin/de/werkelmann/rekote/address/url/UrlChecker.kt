package de.werkelmann.rekote.address.url

import de.werkelmann.rekote.address.NotEmptyRule
import de.werkelmann.rekote.address.ServerConnectionChecker

/**
 * checks for a valid url
 * @see [Wikipedia](https://en.wikipedia.org/wiki/Hostname)
 */
class UrlChecker : ServerConnectionChecker() {
    init {
        rules.add(NotEmptyRule())
        rules.add(UrlLengthRule())
        rules.add(UrlValidCharRule())
        rules.add(UrlLabelsLengthRule())
        rules.add(UrlValidBeginningAndEndRule())
    }
}
