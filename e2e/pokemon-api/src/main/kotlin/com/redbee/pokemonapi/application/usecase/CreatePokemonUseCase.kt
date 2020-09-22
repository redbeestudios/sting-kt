package com.redbee.pokemonapi.application.usecase

import com.redbee.pokemonapi.application.port.`in`.CreatePokemonInPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreatePokemonUseCase : CreatePokemonInPort {

    private val log = LoggerFactory.getLogger(CreatePokemonUseCase::class.java)

    override fun execute(command: CreatePokemonInPort.Command): UUID {
        log.info("Pokemon a crear {}", command.pokemon)
        return UUID.randomUUID()
    }

}
