package com.redbee.pokemonapi.adapter.rest.exception

import com.redbee.pokemonapi.config.exception.GenericException

open class RestClientGenericException(
        errorCode: Int,
        message: String,
        cause: Throwable? = null
) : GenericException(
        errorCode,
        message,
        cause
)
