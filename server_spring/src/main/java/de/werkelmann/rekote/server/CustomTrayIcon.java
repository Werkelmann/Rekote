package de.werkelmann.rekote.server;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;

public class CustomTrayIcon {

    public static void create() {
        if (!SystemTray.isSupported()) {
            return;
        }

        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(getDefaultImage());
        final SystemTray tray = SystemTray.getSystemTray();

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        popup.add(exitItem);
        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Tray icon could not be added");
        }
    }

    static Image getDefaultImage() {
        return createImage("icon.gif", "tray icon");
    }

    private static Image createImage(String path, String description) {
        URL imageURL = CustomTrayIcon.class.getClassLoader().getResource(path);

        if (imageURL != null) {
            return (new ImageIcon(imageURL, description)).getImage();
        }
        throw new RuntimeException("Could not load tray icon");
    }
}
