package de.werkelmann.rekote.client

import de.werkelmann.rekote.address.AddressChecker
import de.werkelmann.rekote.address.ip.IpChecker
import de.werkelmann.rekote.address.port.PortChecker
import de.werkelmann.rekote.address.url.UrlChecker
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ClientAddressValidationTest {

    private val validPort = "8080"
    private val addressChecker = AddressChecker()
    private val urlChecker = UrlChecker()
    private val ipChecker = IpChecker()
    private val portChecker = PortChecker()

    @Test
    fun testSuccessForValidUrl() {
        assertTrue(urlChecker.check("Test-PC"))
    }

    @Test
    fun testSuccessForValidUrlWithDots() {
        assertTrue(urlChecker.check("Test-PC.de"))
    }

    @Test
    fun testSuccessForValidLocalIp() {
        assertTrue(ipChecker.check("192.168.178.12"))
    }

    @Test
    fun testSuccessForShorterValidIp() {
        assertTrue(ipChecker.check("192.168.2.100"))
    }

    @Test
    fun testInvalidUrl() {
        assertFalse(ipChecker.check("invalid url") && urlChecker.check("invalid url"))
    }

    @Test
    fun testExceptionForTooLongUrl() {
        assertFalse(urlChecker.check(veryLongString))
    }

    private val veryLongString: String
        get() {
            val builder = StringBuilder()
            for (i in 0..99) {
                builder.append("tenletters")
            }
            return builder.toString()
        }

    @Test
    fun testFalseForTwoBigNumbersInIp() {
        assertFalse(ipChecker.check("700.900.13.5"))
    }

    @Test
    fun testFalseForTooLongIp() {
        assertFalse(ipChecker.check("192.168.178.30.40"))
    }

    @Test
    fun testFalseForInvalidPortZero() {
        assertFalse(portChecker.check("0"))
    }

    @Test
    fun testExceptionForInvalidPortTooBig() {
        assertFalse(portChecker.check("0123456789"))
    }

    @Test
    fun testExceptionForInvalidPortNotANumber() {
        assertFalse(portChecker.check("invalid"))
    }

    @Test
    fun testValidPort() {
        assertTrue(portChecker.check(validPort))
    }

    @Test
    fun testAddressCheckerWithIP() {
        assertTrue(addressChecker.check("192.168.2.100"))
    }

    @Test
    fun testAddressCheckerWithUrl() {
        assertTrue(addressChecker.check("Test-PC"))
    }
}