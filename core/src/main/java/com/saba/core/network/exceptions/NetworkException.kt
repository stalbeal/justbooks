package com.saba.core.network.exceptions

import java.io.IOException

sealed class NetworkException(
    override val message: String?,
    open val code: Int?
) : IOException(message) {

    data class BadRequest(override val message: String?) :
        NetworkException(message, 400)

    data class Unauthorized(override val message: String?) :
        NetworkException(message, 401)

    data class Forbidden(override val message: String?) :
        NetworkException(message, 403)

    data class NotFound(override val message: String?) :
        NetworkException(message, 404)

    data class APIKeyNotFound(override val message: String?) :
        NetworkException(message, 409)

    data class Internal(override val message: String?) :
        NetworkException(message, 500)

    object Connection : NetworkException("Verify internet connection or host", null)
    object TimeOut : NetworkException("Timeout", null)

    data class UnknowHost(override val message: String?, override val code: Int? = null) :
        NetworkException(message, code)

    data class Unknown(override val message: String?, override val code: Int?) :
        NetworkException(message, code)
}