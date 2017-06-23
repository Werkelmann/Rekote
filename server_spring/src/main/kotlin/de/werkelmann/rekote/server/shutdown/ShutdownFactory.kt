package de.werkelmann.rekote.server.shutdown

import org.apache.commons.lang3.SystemUtils

object ShutdownFactory {

    fun getCorrectShutdown(): AbstractShutdown {
        when {
            SystemUtils.IS_OS_WINDOWS -> return WindowsShutdown()
            SystemUtils.IS_OS_LINUX -> return LinuxShutdown()
            else -> throw RuntimeException("OS not supported")
        }
    }
}