package de.werkelmann.rekote


object Paths {
    const val PROTOCOL_PREFIX = "http://"
    const val BASE = "/rekote/"
    const val INFO = BASE + "info/"
    const val SHUTDOWN_IN = BASE + "shutdown/"
    const val SHUTDOWN_STOP = SHUTDOWN_IN + "stop/"
    const val EVENT = BASE + "event/"
    const val CALL = EVENT + "call/"
    const val SCRIPT = BASE + "script/"
}