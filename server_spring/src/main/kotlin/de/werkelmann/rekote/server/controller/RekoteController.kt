package de.werkelmann.rekote.server.controller

import de.werkelmann.rekote.Paths
import de.werkelmann.rekote.model.HostInfo
import de.werkelmann.rekote.server.dialog.IncomingCallDialog
import de.werkelmann.rekote.server.shutdown.AbstractShutdown
import de.werkelmann.rekote.server.shutdown.ShutdownFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class RekoteController {

    private val shutdown: AbstractShutdown = ShutdownFactory.getCorrectShutdown()

    val info: ResponseEntity<HostInfo>
        @RequestMapping(path = arrayOf(Paths.INFO), method = arrayOf(RequestMethod.GET))
        get() = shutdown.getInfo()

    @RequestMapping(path = arrayOf("${Paths.SHUTDOWN_IN}{minutes}"), method = arrayOf(RequestMethod.GET))
    fun shutdownIn(@PathVariable minutes: Int): ResponseEntity<Void> {
        return shutdown.shutdownIn(minutes)
    }

    @RequestMapping(path = arrayOf(Paths.SHUTDOWN_STOP), method = arrayOf(RequestMethod.GET))
    fun stopShutdown(): ResponseEntity<Void> {
        return shutdown.stopShutdown()
    }

    @RequestMapping(path = arrayOf("${Paths.CALL}{caller}"), method = arrayOf(RequestMethod.GET))
    fun incomingCall(@PathVariable caller: String): ResponseEntity<Void> {
        IncomingCallDialog(caller)
        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(path = arrayOf("${Paths.SCRIPT}{script}"), method = arrayOf(RequestMethod.GET))
    fun runCommand(@PathVariable script: String): ResponseEntity<Void> {
        return shutdown.runScript(script)
    }
}
