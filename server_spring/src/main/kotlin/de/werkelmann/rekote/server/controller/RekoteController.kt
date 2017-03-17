package de.werkelmann.rekote.server.controller

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
@RequestMapping("/rekote")
class RekoteController {

    private val shutdown: AbstractShutdown = ShutdownFactory.getCorrectShutdown()

    val info: ResponseEntity<HostInfo>
        @RequestMapping(path = arrayOf("/info"), method = arrayOf(RequestMethod.GET))
        get() = shutdown.getInfo()

    @RequestMapping(path = arrayOf("/shutdown/{minutes}"), method = arrayOf(RequestMethod.GET))
    fun shutdownIn(@PathVariable minutes: Int): ResponseEntity<Void> {
        return shutdown.shutdownIn(minutes)
    }

    @RequestMapping(path = arrayOf("/shutdown/stop"), method = arrayOf(RequestMethod.GET))
    fun stopShutdown(): ResponseEntity<Void> {
        return shutdown.stopShutdown()
    }

    @RequestMapping(path = arrayOf("/event/call/{caller}"), method = arrayOf(RequestMethod.GET))
    fun incomingCall(@PathVariable caller: String): ResponseEntity<Void> {
        IncomingCallDialog(caller)
        return ResponseEntity(HttpStatus.OK)
    }
}
