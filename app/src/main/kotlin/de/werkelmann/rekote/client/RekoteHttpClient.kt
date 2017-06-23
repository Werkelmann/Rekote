package de.werkelmann.rekote.client

import de.werkelmann.rekote.Paths
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
            return getInfo.execute(buildUrl(Paths.INFO)).get()
        } catch (e: Exception) {
            throw RekoteException("Could not get Info from server")
        }
    }

    override fun stopShutdown(): Boolean {
        return callUrl(Paths.SHUTDOWN_STOP)
    }

    override fun shutdownIn(minutes: Int): Boolean {
        return callUrl(Paths.SHUTDOWN_IN + minutes)
    }

    override fun informAboutCall(caller: String): Boolean {
        return callUrl(Paths.CALL + caller)
    }

    override fun runScript(script: String): Boolean {
        return callUrl(Paths.SCRIPT + script)
    }

    private fun callUrl(path: String): Boolean {
        try {
            val runScript = CallUrlTask()
            runScript.execute(buildUrl(path))
            return runScript.get()
        } catch (e: Exception) {
            return false
        }
    }

    @Throws(MalformedURLException::class)
    private fun buildUrl(pathSuffix: String): URL {
        return URL(Paths.PROTOCOL_PREFIX + hostAddress + ":" + port + pathSuffix)
    }
}
