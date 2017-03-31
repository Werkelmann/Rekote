package de.werkelmann.rekote.call

import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import de.werkelmann.rekote.client.RekoteClient

class CallStateListener(val client: RekoteClient) : PhoneStateListener() {

    private var isFirstCallback = true

    override fun onCallStateChanged(state: Int, incomingNumber: String) {
        Log.i("call", "State: $state $isFirstCallback")
        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> informAboutCall(incomingNumber)
            else -> isFirstCallback = true
        }
    }

    private fun informAboutCall(incomingNumber: String) {
        if (isFirstCallback) {
            val caller = getCaller(incomingNumber)
            client.informAboutCall(caller)
        }
        isFirstCallback = false
    }

    private fun getCaller(incomingNumber: String): String {
        //TODO incomingNumber is always empty, because READ_PHONE_STATE-Permission does not work (on AVD)
        if (incomingNumber == "") {
            return "Unknown"
        }
        return incomingNumber //TODO get caller name
    }
}