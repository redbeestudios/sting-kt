package com.redbee.pokemonapi.adapter.rest

import com.redbee.pokemonapi.adapter.rest.model.TypeVO
import com.redbee.pokemonapi.application.port.out.TypeRepository
import com.redbee.pokemonapi.config.ApiError
import com.redbee.pokemonapi.config.exception.ResourceNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class TypeRestClientAdapter(private val restTemplate: RestTemplate) : TypeRepository {

    private val log = LoggerFactory.getLogger(TypeRestClientAdapter::class.java)

    override fun getType(typeName: String?) = typeName
            .also { log.info("TypeWebService Request: {}", URL_API) }
            .let { restTemplate.getForObject(URL_API, TypeVO::class.java, it) }
            ?.toTypeDomain()
            ?.also { log.info("TypeWebService Response: $it") }
            ?: throw ResourceNotFoundException(ApiError.RESOURCE_NOT_FOUND.errorCode, "No se encontro el tipo $typeName")

    companion object {
        private const val URL_API = "https://pokeapi.co/api/v2/type/{type}"
    }

}
