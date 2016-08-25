package de.werkelmann.rekote.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import de.werkelmann.rekote.model.HostInfo;
import de.werkelmann.rekote.util.RekoteException;


@RestController
@RequestMapping("/rekote")
public class Controller {

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public ResponseEntity<HostInfo> getInfo() {
        try {
            HostInfo info = new HostInfo();
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (RekoteException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/shutdown", method = RequestMethod.GET)
    public ResponseEntity<Void> instantShutdown() {
        return shutdownIn(0);
    }

    @RequestMapping(path = "/shutdown/{seconds}", method = RequestMethod.GET)
    public ResponseEntity<Void> shutdownIn(@PathVariable int seconds) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("shutdown -s -t " + seconds);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/shutdown/stop", method = RequestMethod.GET)
    public ResponseEntity<Void> stopShutdown() throws IOException {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("shutdown -a");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
