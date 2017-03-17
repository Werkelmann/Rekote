package de.werkelmann.rekote.server.shutdown

import org.apache.commons.lang3.SystemUtils

class ShutdownFactory {

    companion object {
        @JvmStatic fun getCorrectShutdown(): AbstractShutdown {
            if (SystemUtils.IS_OS_WINDOWS) return WindowsShutdown()
            if (SystemUtils.IS_OS_LINUX) return LinuxShutdown()
            throw RuntimeException("OS not supported")
        }
    }
}