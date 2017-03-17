package de.werkelmann.rekote.server.dialog

import de.werkelmann.rekote.address.port.PortChecker
import de.werkelmann.rekote.server.ServerMain
import java.util.prefs.Preferences
import javax.swing.JDialog
import javax.swing.JOptionPane

class PortDialog : JDialog() {

    private val KEY_PORT = "server.port"
    private val preferences = Preferences.userNodeForPackage(ServerMain::class.java)

    init {
        val oldPort = preferences[KEY_PORT, "8090"]
        val input = JOptionPane.showInputDialog(null, "Enter Port", "Rekote",
                JOptionPane.PLAIN_MESSAGE, null, null, oldPort)
        val newPort = input?.toString() ?: oldPort
        preferences.put(KEY_PORT, validatePort(newPort, oldPort))
        preferences.sync()
    }

    private fun validatePort(newPort: String, oldPort: String): String {
        if (PortChecker().check(newPort)) {
            return newPort
        }
        JOptionPane.showMessageDialog(null, "You entered an invalid port. Try again",
                "Invalid Port", JOptionPane.ERROR_MESSAGE)
        return oldPort
    }
}
