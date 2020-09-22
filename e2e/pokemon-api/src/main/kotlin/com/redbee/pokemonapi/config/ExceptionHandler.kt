package com.redbee.pokemonapi.config

import com.redbee.pokemonapi.adapter.rest.exception.RestClientGenericException
import com.redbee.pokemonapi.adapter.rest.exception.TimeoutRestClientException
import com.redbee.pokemonapi.config.exception.GenericException
import com.redbee.pokemonapi.config.exception.ResourceNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler(
        private val httpServletRequest: HttpServletRequest
) {

    private val log = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @Value("\${spring.profiles.active:}")
    private val activeProfile: String = ""

    @ExceptionHandler(Throwable::class)
    fun handle(ex: Throwable): ResponseEntity<ApiErrorResponse> {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase, ex)
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex, ApiError.INTERNAL_ERROR.errorCode)
    }

    @ExceptionHandler(GenericException::class)
    fun handle(ex: GenericException): ResponseEntity<ApiErrorResponse> {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase, ex)
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex, ex.errorCode)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handle(ex: ResourceNotFoundException): ResponseEntity<ApiErrorResponse> {
        log.error(HttpStatus.NOT_FOUND.reasonPhrase, ex)
        return buildResponseError(HttpStatus.NOT_FOUND, ex, ex.errorCode)
    }

    @ExceptionHandler(RestClientGenericException::class)
    fun handle(ex: RestClientGenericException): ResponseEntity<ApiErrorResponse> {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase, ex)
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex,ex.errorCode)
    }

    @ExceptionHandler(TimeoutRestClientException::class)
    fun handle(ex: TimeoutRestClientException): ResponseEntity<ApiErrorResponse> {
        log.error(HttpStatus.REQUEST_TIMEOUT.reasonPhrase, ex)
        return buildResponseError(HttpStatus.REQUEST_TIMEOUT, ex, ex.errorCode)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handle(ex: AccessDeniedException): ResponseEntity<ApiErrorResponse> {
        log.error(HttpStatus.FORBIDDEN.reasonPhrase, ex)
        return buildResponseError(HttpStatus.FORBIDDEN, ex, ApiError.ACCESS_DENIED.errorCode)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(ex: MethodArgumentNotValidException) = ex
            .also { log.error(HttpStatus.BAD_REQUEST.reasonPhrase, it) }
            .let {
                val message: String = it.bindingResult.allErrors.joinToString { error -> "${error.defaultMessage}" }
                buildResponseError(HttpStatus.BAD_REQUEST, it, ApiError.INVALID_COMMAND.errorCode, message)
            }


    private fun buildResponseError(
            httpStatus: HttpStatus,
            ex: Throwable,
            errorCode: Int,
            errorMessage: String = ex.message ?: ""): ResponseEntity<ApiErrorResponse> {

        val debugMessage: String = activeProfile
                .takeUnless { it.contains(PROD_PROFILE) }
                ?.let { Arrays.toString(ex.stackTrace) }
                ?: ex.localizedMessage

        val apiErrorResponse = ApiErrorResponse(
                timestamp = LocalDateTime.now(ZoneOffset.UTC),
                name = httpStatus.reasonPhrase,
                message = errorMessage,
                status = httpStatus.value(),
                code = errorCode,
                resource = httpServletRequest.requestURI,
                stackTrace = debugMessage
        )

        return ResponseEntity(apiErrorResponse, httpStatus)
    }

    data class ApiErrorResponse(
            val name: String,
            val status: Int,
            val timestamp: LocalDateTime,
            val code: Int,
            val resource: String,
            val message: String,
            val stackTrace: String
    )

    companion object {
        private const val PROD_PROFILE = "prod"
    }
}
