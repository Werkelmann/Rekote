package de.werkelmann.rekote.client.tasks

import android.os.AsyncTask

import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class CallUrlTask : AsyncTask<URL, Void, Boolean>() {
    override fun doInBackground(vararg params: URL): Boolean? {
        try {
            val url = params[0]
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 5000
            connection.connect()
            return connection.responseCode == 200
        } catch (e: IOException) {
            return false
        }

    }
}
