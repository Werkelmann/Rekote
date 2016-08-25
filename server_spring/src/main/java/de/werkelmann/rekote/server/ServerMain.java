package de.werkelmann.rekote.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerMain {

    public static void main(String[] args) {
        CustomTrayIcon.create();
        SpringApplication.run(ServerMain.class, args);
    }
}