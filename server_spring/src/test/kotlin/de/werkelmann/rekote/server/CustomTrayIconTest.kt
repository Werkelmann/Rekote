package de.werkelmann.rekote.server

import org.junit.Assert.assertNotNull
import org.junit.Test

class CustomTrayIconTest {

    @Test
    fun testIfDefaultIconCouldBeLoaded() {
        val i = CustomTrayIcon.defaultImage
        assertNotNull(i)
    }
}