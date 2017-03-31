package de.werkelmann.rekote.client

import de.werkelmann.rekote.address.AddressChecker
import de.werkelmann.rekote.address.ip.IpChecker
import de.werkelmann.rekote.address.port.PortChecker
import de.werkelmann.rekote.client.tasks.CallUrlTask
import de.werkelmann.rekote.client.tasks.GetInfoTask
import de.werkelmann.rekote.model.HostInfo
import de.werkelmann.rekote.util.RekoteException
import java.net.MalformedURLException
import java.net.URL

class RekoteHttpClient @Throws(RekoteException::class)
constructor(address: String, port: String) : RekoteClient {

    private val hostAddress: String
    private val port: String

    init {
        if (isValidAddress(address) && isValidPort(port)) {
            this.hostAddress = address
            this.port = port
        } else throw RekoteException("Invalid server address")
    }

    private fun isValidAddress(address: String): Boolean {
        return AddressChecker().check(address) || IpChecker().check(address)
    }

    private fun isValidPort(port: String): Boolean {
        return PortChecker().check(port)
    }

    @Throws(RekoteException::class)
    override fun getHostInfo(): HostInfo {
        try {
            val getInfo = GetInfoTask()
            return getInfo.execute(buildUrl(PATH_INFO)).get()
        } catch (e: Exception) {
            throw RekoteException("Could not get Info from server")
        }
    }

    override fun stopShutdown(): Boolean {
        try {
            val stopShutdown = CallUrlTask()
            stopShutdown.execute(buildUrl(PATH_SHUTDOWN_STOP))
            return stopShutdown.get()
        } catch (e: Exception) {
            return false
        }

    }

    @Throws(MalformedURLException::class)
    private fun buildUrl(pathSuffix: String): URL {
        return URL(PROTOCOL_PREFIX + hostAddress + ":" + port + pathSuffix)
    }

    override fun shutdownIn(minutes: Int): Boolean {
        try {
            val stopShutdown = CallUrlTask()
            stopShutdown.execute(buildUrl(PATH_SHUTDOWN_IN + minutes))
            return stopShutdown.get()
        } catch (e: Exception) {
            return false
        }

    }

    override fun informAboutCall(caller: String): Boolean {
        try {
            val informAboutCall = CallUrlTask()
            informAboutCall.execute(buildUrl(PATH_CALL + caller))
            return informAboutCall.get()
        } catch (e: Exception) {
            return false
        }

    }

    //TODO move to commons, if server annotations can use them too
    companion object {
        private val PROTOCOL_PREFIX = "http://"
        private val PATH = "/rekote/"
        private val PATH_INFO = PATH + "info/"
        private val PATH_SHUTDOWN_IN = PATH + "shutdown/"
        private val PATH_SHUTDOWN_STOP = PATH_SHUTDOWN_IN + "stop/"
        private val PATH_EVENT = PATH + "event/"
        private val PATH_CALL = PATH_EVENT + "call/"
    }
}
