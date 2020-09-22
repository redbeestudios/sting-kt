package com.redbee.pokemonapi.application.port.`in`

import com.redbee.pokemonapi.domain.Pokemon
import java.util.*

interface CreatePokemonInPort {

    infix fun execute(command: Command): UUID

    data class Command(val pokemon: Pokemon)
}
