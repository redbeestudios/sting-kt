package com.redbee.pokemonapi.adapter.rest

import com.redbee.pokemonapi.adapter.rest.model.PokemonVO
import com.redbee.pokemonapi.application.port.out.PokemonRepository
import com.redbee.pokemonapi.config.ApiError
import com.redbee.pokemonapi.config.exception.ResourceNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class PokemonRestClientAdapter(private val restTemplate: RestTemplate) : PokemonRepository {

    private val log = LoggerFactory.getLogger(PokemonRestClientAdapter::class.java)

    override fun getPokemon(name: String) = name
            .also { log.info("PokemonWebService Request: {}", URL_POKEMON) }
            .let { restTemplate.getForObject(URL_POKEMON, PokemonVO::class.java, it) }
            ?.toPokemonDomain()
            ?.also { log.info("PokemonWebService Response: $it") }
            ?: throw ResourceNotFoundException(ApiError.RESOURCE_NOT_FOUND.errorCode, "No se encontro el pokemon $name")

    companion object {
        private const val URL_POKEMON = "https://pokeapi.co/api/v2/pokemon/{name}"
    }

}
