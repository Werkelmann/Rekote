package de.werkelmann.rekote.address

import de.werkelmann.rekote.address.ip.IpChecker
import de.werkelmann.rekote.address.url.UrlChecker

class AddressChecker : ServerConnectionChecker() {

    val urlChecker = UrlChecker()
    val ipChecker = IpChecker()

    override fun check(address: String?): Boolean {
        return urlChecker.check(address) || ipChecker.check(address)
    }
}


