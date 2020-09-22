package com.redbee.pokemonapi.application.port.out

import com.redbee.pokemonapi.domain.Pokemon

interface PokemonRepository {
    fun getPokemon(name: String): Pokemon
}
