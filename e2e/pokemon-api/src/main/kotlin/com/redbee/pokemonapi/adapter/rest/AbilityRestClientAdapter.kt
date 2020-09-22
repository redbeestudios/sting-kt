package com.redbee.pokemonapi.adapter.rest

import com.redbee.pokemonapi.adapter.rest.model.AbilityVO
import com.redbee.pokemonapi.application.port.out.AbilityRepository
import com.redbee.pokemonapi.config.ApiError
import com.redbee.pokemonapi.config.exception.ResourceNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class AbilityRestClientAdapter(
        private val restTemplate: RestTemplate
) : AbilityRepository {

    private val log = LoggerFactory.getLogger(AbilityRestClientAdapter::class.java)

    override fun getAbility(name: String) = name
            .also { log.info("AbilityWebService Request: {}", API_URL) }
            .let { restTemplate.getForObject(API_URL, AbilityVO::class.java, it) }
            ?.toAbilityDomain()
            ?.also { log.info("Llamado a ability por rest terminado, {}", it) }
            ?: throw ResourceNotFoundException(ApiError.RESOURCE_NOT_FOUND.errorCode, "No se encontro la habilidad $name")

    companion object {
        private const val API_URL = "https://pokeapi.co/api/v2/ability/{ability}"
    }

}
