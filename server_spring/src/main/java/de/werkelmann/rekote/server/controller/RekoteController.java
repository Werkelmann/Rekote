package de.werkelmann.rekote.server.controller;

import de.werkelmann.rekote.model.HostInfo;
import de.werkelmann.rekote.server.shutdown.AbstractShutdown;
import de.werkelmann.rekote.server.shutdown.ShutdownFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rekote")
public class RekoteController {

    private AbstractShutdown shutdown;

    public RekoteController() {
        shutdown = ShutdownFactory.getCorrectShutdown();
    }

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public ResponseEntity<HostInfo> getInfo() {
        return shutdown.getInfo();
    }

    @RequestMapping(path = "/shutdown/{minutes}", method = RequestMethod.GET)
    public ResponseEntity<Void> shutdownIn(@PathVariable int minutes) {
        return shutdown.shutdownIn(minutes);
    }

    @RequestMapping(path = "/shutdown/stop", method = RequestMethod.GET)
    public ResponseEntity<Void> stopShutdown() {
        return shutdown.stopShutdown();
    }
}
