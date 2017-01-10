package de.werkelmann.rekote.client;

import de.werkelmann.rekote.address.ip.IpChecker;
import de.werkelmann.rekote.address.port.PortChecker;
import de.werkelmann.rekote.address.url.UrlChecker;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClientAddressValidationTest {

    private final String validPort = "8080";
    private final UrlChecker urlChecker = new UrlChecker();
    private final IpChecker ipChecker = new IpChecker();
    private final PortChecker portChecker = new PortChecker();

    @Test
    public void testSuccessForValidUrl() {
        assertTrue(urlChecker.check("Test-PC"));
    }

    @Test
    public void testSuccessForValidUrlWithDots() {
        assertTrue(urlChecker.check("Test-PC.de"));
    }

    @Test
    public void testSuccessForValidIp() {
        assertTrue(ipChecker.check("192.168.178.12"));
    }

    @Test
    public void testInvalidUrl() {
        assertFalse(ipChecker.check("invalid url") && urlChecker.check("invalid url"));
    }

    @Test
    public void testExceptionForTooLongUrl() {
        assertFalse(urlChecker.check(getVeryLongString()));
    }

    private String getVeryLongString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append("tenletters");
        }
        return builder.toString();
    }

    @Test
    public void testFalseForTwoBigNumbersInIp() {
        assertFalse(ipChecker.check("700.900.13.5"));
    }

    @Test
    public void testFalseForTooLongIp() {
        assertFalse(ipChecker.check("192.168.178.30.40"));
    }

    @Test
    public void testFalseForInvalidPortZero() {
        assertFalse(portChecker.check("0"));
    }

    @Test
    public void testExceptionForInvalidPortTooBig() {
        assertFalse(portChecker.check("0123456789"));
    }

    @Test
    public void testExceptionForInvalidPortNotANumber() {
        assertFalse(portChecker.check("invalid"));
    }
}