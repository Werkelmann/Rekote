package de.werkelmann.rekote.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import java.util.*
import java.util.prefs.Preferences

@SpringBootApplication
open class ServerMain

val KEY_PORT = "server.port"

fun main(args: Array<String>) {
    CustomTrayIcon.create()
    val properties = Properties()
    val preferences = Preferences.userNodeForPackage(ServerMain::class.java)
    val port = preferences[KEY_PORT, "8090"]
    properties[KEY_PORT] = Integer.parseInt(port)

    SpringApplicationBuilder()
            .sources(ServerMain::class.java)
            .properties(properties)
            .run(*args)
}