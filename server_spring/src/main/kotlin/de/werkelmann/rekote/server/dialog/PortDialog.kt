package de.werkelmann.rekote.server.dialog

import de.werkelmann.rekote.server.ServerMain
import java.util.prefs.Preferences
import javax.swing.JDialog
import javax.swing.JOptionPane

class PortDialog : JDialog() {

    private val KEY_PORT = "server.port"
    private val preferences = Preferences.userNodeForPackage(ServerMain::class.java)

    init {
        val port = preferences[KEY_PORT, "8090"]
        val input = JOptionPane.showInputDialog(null, "Enter Port", "Rekote",
                JOptionPane.PLAIN_MESSAGE, null, null, port)
        val newPort = input?.toString() ?: port
        preferences.put(KEY_PORT, newPort)
        preferences.sync()
    }

}
