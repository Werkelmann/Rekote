package de.werkelmann.rekote.server;

import org.junit.Test;

import java.awt.Image;

import static org.junit.Assert.*;

public class CustomTrayIconTest {

    @Test
    public void testIfDefaultIconCouldBeLoaded() {
        Image i = CustomTrayIcon.getDefaultImage();
        assertNotNull(i);
    }
}