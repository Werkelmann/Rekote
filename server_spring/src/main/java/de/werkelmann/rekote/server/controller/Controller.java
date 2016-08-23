package de.werkelmann.rekote.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.werkelmann.rekote.server.model.HostInfo;

@RestController
@RequestMapping("/rekote")
public class Controller {

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public HostInfo getInfo() {
        return HostInfo.getInfo();
    }
}
