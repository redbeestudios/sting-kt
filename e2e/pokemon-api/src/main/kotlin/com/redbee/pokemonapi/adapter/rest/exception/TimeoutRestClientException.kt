package com.redbee.pokemonapi.adapter.rest.exception

import com.redbee.pokemonapi.adapter.rest.exception.RestClientGenericException

class TimeoutRestClientException(
        errorCode: Int,
        message: String,
        cause: Throwable? = null
) : RestClientGenericException(
        errorCode,
        message,
        cause
)


