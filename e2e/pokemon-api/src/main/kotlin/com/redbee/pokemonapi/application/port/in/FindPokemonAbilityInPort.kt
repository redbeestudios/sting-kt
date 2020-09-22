package com.redbee.pokemonapi.application.port.`in`

import com.redbee.pokemonapi.domain.Pokemon

interface FindPokemonAbilityInPort {
    infix fun by(name: String): Pokemon
}
