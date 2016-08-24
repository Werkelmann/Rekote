package de.werkelmann.rekote.server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import de.werkelmann.rekote.server.model.HostInfo;

@RestController
@RequestMapping("/rekote")
public class Controller {

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public HostInfo getInfo() {
        return HostInfo.getInfo();
    }

    @RequestMapping(path = "/shutdown", method = RequestMethod.GET)
    public void instantShutdown() throws IOException {
        shutdownIn(0);
    }

    @RequestMapping(path = "/shutdown/{seconds}", method = RequestMethod.GET)
    public void shutdownIn(@PathVariable int seconds) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("shutdown -s -t " + seconds);
    }

    @RequestMapping(path = "/shutdown/stop", method = RequestMethod.GET)
    public void stopShutdown() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("shutdown -a");
    }
}
