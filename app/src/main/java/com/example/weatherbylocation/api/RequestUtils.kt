package com.example.weatherbylocation.api

import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.net.URLConnection
import java.net.URLEncoder

/**
 * Static utility class for network related operations.
 */
object RequestUtils {

    private const val CHARSET = "UTF-8"

    /**
     * @param param The request parameter to encode in URL-readable format.
     * @return The url-encoded request parameter as string.
     */
    private fun encode(param: String) = URLEncoder.encode(param, CHARSET)

    /**
     * Writes the specified key-value pairs as request parameters
     * to the output stream of the specified URLConnection.
     *
     * @param connection URLConnection object that will be used for the network request.
     * @param paramPairs A vararg of key-value pairs representing the request parameters.
     */
    fun writeRequestParams(connection: URLConnection, vararg paramPairs: Pair<String, String>) {
        if (paramPairs.isEmpty())
            return

        try {
            connection.doOutput = true
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            return
        }

        val paramsBuilder = StringBuilder()
        var isFirstPair = true
        for (pair in paramPairs) {
            if (isFirstPair)
                isFirstPair = false
            else
                paramsBuilder.append('&')

            paramsBuilder.append(encode(pair.first)).append('=').append(encode(pair.second))
        }

        var outputStream: OutputStream? = null
        var writer: BufferedWriter? = null

        try {
            outputStream = connection.getOutputStream()
            writer = BufferedWriter(OutputStreamWriter(outputStream, CHARSET))
            writer.write(paramsBuilder.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                writer?.flush()
                writer?.close()
                outputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}