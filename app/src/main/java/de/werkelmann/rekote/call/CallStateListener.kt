package de.werkelmann.rekote.call

import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import de.werkelmann.rekote.client.RekoteClient

class CallStateListener(val client: RekoteClient) : PhoneStateListener() {

    override fun onCallStateChanged(state: Int, incomingNumber: String) {
        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> client.informAboutCall(incomingNumber)
        }
    }
}