package de.werkelmann.rekote.server.shutdown

class WindowsShutdown : AbstractShutdown() {

    override fun buildShutdownStopCommand(): String {
        return "shutdown -a"
    }

    override fun buildShutdownCommand(timeInSeconds: Int): String {
        return "shutdown -s -t $timeInSeconds"
    }

}