package de.werkelmann.rekote.server.shutdown

class LinuxShutdown : AbstractShutdown() {

    override fun buildShutdownCommand(timeInSeconds: Int): String {
        val time = if (timeInSeconds > 0) timeInSeconds.toString() else "now"
        return "shutdown -h $time"
    }

    override fun buildShutdownStopCommand(): String {
        throw UnsupportedOperationException("not implemented")
    }

}