package de.werkelmann.rekote.client;

import de.werkelmann.rekote.model.HostInfo;

public interface RekoteClient {

    HostInfo getHostInfo();
    boolean stopShutdown();
    boolean shutdownIn(int minutes);
}
