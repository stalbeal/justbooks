package com.saba.core.network

import com.saba.core.network.exceptions.NetworkException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = try {
            chain.proceed(request)
        } catch (e: IOException) {
            throw when (e) {
                is ConnectException -> NetworkException.Connection
                is TimeoutException,
                is SocketTimeoutException -> NetworkException.TimeOut
                is UnknownHostException -> NetworkException.UnknowHost(e.message)
                else -> e
            }
        }

        if (!response.isSuccessful) {
            val exception: NetworkException = when (response.code) {
                HttpURLConnection.HTTP_BAD_REQUEST -> NetworkException.BadRequest(response.message)
                HttpURLConnection.HTTP_UNAUTHORIZED -> NetworkException.Unauthorized(response.message)
                HttpURLConnection.HTTP_NOT_FOUND -> NetworkException.NotFound(response.message)
                HttpURLConnection.HTTP_FORBIDDEN -> NetworkException.Forbidden(response.message)
                HttpURLConnection.HTTP_CONFLICT -> NetworkException.APIKeyNotFound("Api key not found")
                HttpURLConnection.HTTP_INTERNAL_ERROR -> NetworkException.Internal(response.message)
                else -> NetworkException.Unknown(response.message, response.code)
                //429 exceso de queries
            }

            throw exception
        }

        return response
    }
}


