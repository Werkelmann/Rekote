package de.werkelmann.rekote.server.dialog

import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
import javax.swing.JDialog
import javax.swing.JOptionPane

class PortDialog : JDialog() {

    private val KEY_PORT = "server.port"
    private val properties: Properties

    init {
        properties = Properties()
        loadProperties()

        val port = properties[KEY_PORT]
        val newPort = JOptionPane.showInputDialog(null, "Enter Port", "Rekote",
                JOptionPane.PLAIN_MESSAGE, null, null, port)
        properties[KEY_PORT] = newPort ?: port
        storeProperties()
    }

    private fun loadProperties() {
        val propFileStream = BufferedInputStream(FileInputStream(getPropFile()))
        properties.load(propFileStream)
        propFileStream.close()
    }

    private fun storeProperties() {
        val outputStream = FileOutputStream(getPropFile())
        val date = Date()
        properties.store(outputStream, "Changed at $date") //TODO storing not working yet
        outputStream.close()
    }

    private fun getPropFile(): File {
        return File(javaClass.classLoader.getResource("application.properties").toURI())
    }
}
