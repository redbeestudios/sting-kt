package com.redbee.pokemonapi.adapter.controller

import com.redbee.pokemonapi.adapter.controller.model.PokemonRest
import com.redbee.pokemonapi.application.port.`in`.CreatePokemonInPort
import com.redbee.pokemonapi.application.port.`in`.FindPokemonAbilityInPort
import com.redbee.pokemonapi.domain.Pokemon
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1/pokemon")
class PokemonControllerAdapter(
    private val findPokemonAbility: FindPokemonAbilityInPort,
    private val createPokemon: CreatePokemonInPort
) {

    private val log = LoggerFactory.getLogger(PokemonControllerAdapter::class.java)

    @GetMapping("/{name}")
    fun find(@PathVariable("name") name: String) = name
            .also { log.info("Llamada al servicio /pokemon/{}", it) }
            .let { findPokemonAbility by it }
            .let { PokemonRest from it }
            .also { log.info("Respuesta servicio getPokemon: {}", it) }

    @PostMapping
    fun create() = Pokemon(name = "pikachu")
            .let { CreatePokemonInPort.Command(it) }
            .let { createPokemon execute it }
}
