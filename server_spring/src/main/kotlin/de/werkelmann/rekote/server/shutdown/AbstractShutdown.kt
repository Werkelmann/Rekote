package de.werkelmann.rekote.server.shutdown

import de.werkelmann.rekote.model.HostInfo
import de.werkelmann.rekote.util.RekoteException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.io.IOException

abstract class AbstractShutdown {

    abstract fun buildShutdownCommand(timeInSeconds: Int): String
    abstract fun buildShutdownStopCommand(): String

    fun shutdownIn(timeInMinutes: Int): ResponseEntity<Void> {
        try {
            val runtime = Runtime.getRuntime()
            runtime.exec(buildShutdownCommand(asSeconds(timeInMinutes)))
            return ResponseEntity(HttpStatus.OK)
        } catch (e: IOException) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun stopShutdown(): ResponseEntity<Void> {
        try {
            val runtime = Runtime.getRuntime()
            runtime.exec(buildShutdownStopCommand())
            return ResponseEntity(HttpStatus.OK)
        } catch (e: IOException) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun getInfo(): ResponseEntity<HostInfo> {
        try {
            val info = HostInfo()
            return ResponseEntity(info, HttpStatus.OK)
        } catch (e: RekoteException) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    protected fun asSeconds(timeInMinutes: Int): Int {
        return timeInMinutes * 60
    }
}
