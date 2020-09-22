package com.redbee.pokemonapi.config.exception

import com.redbee.pokemonapi.config.exception.GenericException

open class ResourceNotFoundException(
        errorCode: Int,
        message: String,
        cause: Throwable? = null
) : GenericException(
        errorCode,
        message,
        cause
)
