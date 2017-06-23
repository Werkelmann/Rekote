package de.werkelmann.rekote.client

import de.werkelmann.rekote.model.HostInfo

interface RekoteClient {
    fun getHostInfo(): HostInfo
    fun stopShutdown(): Boolean
    fun shutdownIn(minutes: Int): Boolean
    fun informAboutCall(caller: String): Boolean
    fun runScript(script: String): Boolean
}
