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
public class RekoteController {

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public ResponseEntity<HostInfo> getInfo() {
        try {
            HostInfo info = new HostInfo();
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (RekoteException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/shutdown/{minutes}", method = RequestMethod.GET)
    public ResponseEntity<Void> shutdownIn(@PathVariable int minutes) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("shutdown -s -t " + asSeconds(minutes));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int asSeconds(int minutes) {
        return minutes * 60;
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