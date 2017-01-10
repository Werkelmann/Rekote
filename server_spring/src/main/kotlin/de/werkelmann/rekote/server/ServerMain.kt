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
    properties[KEY_PORT] = preferences[KEY_PORT, "8090"]
    SpringApplicationBuilder()
            .sources(ServerMain::class.java)
            .properties(properties)
            .run(*args)
}
