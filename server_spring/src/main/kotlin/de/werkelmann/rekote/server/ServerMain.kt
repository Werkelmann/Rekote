package de.werkelmann.rekote.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class ServerMain {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            CustomTrayIcon.create()
            SpringApplication.run(ServerMain::class.java, *args)
        }
    }
}