package de.werkelmann.rekote.server.dialog

import javax.swing.JOptionPane

class IncomingCallDialog(caller: String) {

    init {
        JOptionPane.showMessageDialog(null, "Incoming call from $caller!")
    }
}