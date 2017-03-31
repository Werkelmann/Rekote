package de.werkelmann.rekote.client.tasks

import android.os.AsyncTask
import de.werkelmann.rekote.model.HostInfo
import org.json.JSONException
import org.json.JSONObject

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GetInfoTask : AsyncTask<URL, Void, HostInfo>() {

    override fun doInBackground(vararg params: URL): HostInfo? {
        val connection: HttpURLConnection
        try {
            val url = params[0]
            connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 5000
            connection.connect()
            if (connection.responseCode == 200) {
                val message = readFromConnection(connection)
                return HostInfo(message)
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }

    @Throws(IOException::class, JSONException::class)
    private fun readFromConnection(connection: HttpURLConnection): JSONObject {
        val json = readStringFromConnection(connection)
        return JSONObject(json)
    }

    @Throws(IOException::class)
    private fun readStringFromConnection(connection: HttpURLConnection): String {

        val input = InputStreamReader(connection.content as InputStream)
        val buff = BufferedReader(input)
        return readFromBuffer(buff)
    }

    private fun readFromBuffer(buff: BufferedReader): String {
        val builder = StringBuilder()
        var line: String? = buff.readLine()
        while (line != null) {
            builder.append(line)
            line = buff.readLine()
        }
        return builder.toString()
    }

}
