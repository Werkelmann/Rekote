package de.werkelmann.rekote.model

import de.werkelmann.rekote.util.RekoteException
import org.json.JSONException
import org.json.JSONObject

import java.net.InetAddress
import java.net.UnknownHostException

class HostInfo {

    val hostName: String
    val ipAddress: String

    @Throws(RekoteException::class)
    constructor() {
        try {
            this.hostName = InetAddress.getLocalHost().hostName
            this.ipAddress = InetAddress.getLocalHost().hostAddress
        } catch (e: UnknownHostException) {
            throw RekoteException("HostInfo could not get initialized")
        }

    }

    @Throws(RekoteException::class)
    constructor(message: JSONObject) {
        try {
            this.hostName = message.getString("hostName")
            this.ipAddress = message.getString("ipAddress")
        } catch (e: JSONException) {
            throw RekoteException("HostInfo could not get initialized")
        }

    }

    override fun toString(): String {
        return hostName + " " + ipAddress
    }
}
