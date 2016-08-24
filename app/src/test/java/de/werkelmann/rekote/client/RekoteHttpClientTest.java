package de.werkelmann.rekote.client;

import org.junit.Test;

import de.werkelmann.rekote.util.RekoteException;

import static org.junit.Assert.*;

public class RekoteHttpClientTest {

    @Test
    public void testSuccessForValidUrl() {
        try {
            RekoteHttpClient client = new RekoteHttpClient("Test-PC");
            assertNotNull(client);
        } catch (RekoteException e) {
            fail("RekoteHttpClient threw Exception for valid URL Test-PC");
        }
    }

    @Test
    public void testSuccessForValidUrlWithDots() {
        try {
            RekoteHttpClient client = new RekoteHttpClient("Test-PC.de");
            assertNotNull(client);
        } catch (RekoteException e) {
            fail("RekoteHttpClient threw Exception for valid URL Test-PC.de");
        }
    }

    @Test
    public void testSuccessForValidIp() {
        try {
            RekoteHttpClient client = new RekoteHttpClient("192.168.178.12");
            assertNotNull(client);
        } catch (RekoteException e) {
            fail("RekoteHttpClient threw Exception for valid IP 192.168.178.12");
        }
    }

    @Test
    public void testExceptionForInvalidUrlWithUnderscore() {
        try {
            RekoteHttpClient client = new RekoteHttpClient("invalid_url");
            fail("RekoteHttpClient did not throw Exception for invalid URL invalid_url");
        } catch (RekoteException e) {
            assertEquals("Invalid server address", e.getMessage());
        }
    }

    @Test
    public void testExceptionForTooLongUrl() {
        try {
            RekoteHttpClient client = new RekoteHttpClient(getVeryLongString());
            fail("RekoteHttpClient did not throw Exception for a too long URL");
        } catch (RekoteException e) {
            assertEquals("Invalid server address", e.getMessage());
        }
    }

    private String getVeryLongString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append("tenletters");
        }
        return builder.toString();
    }

    @Test
    public void testExceptionForInvalidIpWithFalseNumbers() {
        try {
            RekoteHttpClient client = new RekoteHttpClient("700.800.9.123");
            fail("RekoteHttpClient did not throw Exception for a wrong IP");
        } catch (RekoteException e) {
            assertEquals("Invalid server address", e.getMessage());
        }
    }

    @Test
    public void testExceptionForTooLongIp() {
        try {
            RekoteHttpClient client = new RekoteHttpClient("192.168.178.30.40");
            fail("RekoteHttpClient did not throw Exception for a too long IP");
        } catch (RekoteException e) {
            assertEquals("Invalid server address", e.getMessage());
        }
    }
}