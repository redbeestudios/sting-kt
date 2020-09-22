package com.redbee.pokemonapi.config.rest

import com.redbee.pokemonapi.adapter.rest.exception.RestClientGenericException
import com.redbee.pokemonapi.adapter.rest.exception.TimeoutRestClientException
import com.redbee.pokemonapi.config.exception.ResourceNotFoundException
import com.redbee.pokemonapi.config.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler

class RestTemplateErrorHandler : ResponseErrorHandler {

    override fun hasError(response: ClientHttpResponse): Boolean {
        return response.statusCode.isError
    }

    override fun handleError(response: ClientHttpResponse) = throw when (response.statusCode) {
        HttpStatus.NOT_FOUND -> ResourceNotFoundException(ApiError.RESOURCE_NOT_FOUND.errorCode, ApiError.RESOURCE_NOT_FOUND.defaultMessage)
        HttpStatus.REQUEST_TIMEOUT -> TimeoutRestClientException(ApiError.TIMEOUT_FOUND.errorCode, ApiError.TIMEOUT_FOUND.defaultMessage)
        else -> RestClientGenericException(ApiError.INTERNAL_ERROR.errorCode, ApiError.INTERNAL_ERROR.defaultMessage)
    }

}
