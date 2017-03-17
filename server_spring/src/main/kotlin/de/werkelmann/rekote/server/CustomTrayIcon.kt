package de.werkelmann.rekote.server

import de.werkelmann.rekote.server.dialog.PortDialog
import java.awt.*
import javax.swing.ImageIcon

object CustomTrayIcon {

    fun create() {
        if (!SystemTray.isSupported()) {
            return
        }

        val popup = PopupMenu()
        val trayIcon = TrayIcon(defaultImage)
        val tray = SystemTray.getSystemTray()

        val portDialogItem = MenuItem("Port")
        portDialogItem.addActionListener { PortDialog() }

        val exitItem = MenuItem("Exit")
        exitItem.addActionListener { System.exit(0) }

        popup.add(portDialogItem)
        popup.add(exitItem)
        trayIcon.popupMenu = popup

        try {
            tray.add(trayIcon)
        } catch (e: AWTException) {
            println("Tray icon could not be added")
        }

    }

    internal val defaultImage: Image
        get() = createImage("icon.gif", "tray icon")

    private fun createImage(path: String, description: String): Image {
        val imageURL = CustomTrayIcon::class.java.classLoader.getResource(path)

        if (imageURL != null) {
            return ImageIcon(imageURL, description).image
        }
        throw RuntimeException("Could not load tray icon")
    }
}
