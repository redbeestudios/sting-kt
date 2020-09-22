package com.redbee.pokemonapi.adapter.controller.model

import com.redbee.pokemonapi.domain.Pokemon

data class PokemonRest(
    val name: String,
    val ability: AbilityRest,
    val type: TypeRest
) {

    companion object {
        infix fun from(pokemon: Pokemon): PokemonRest {
            return PokemonRest(
                    name = pokemon.name,
                    ability = AbilityRest.toAbilityRest(pokemon.ability),
                    type = TypeRest.toTypeRest(pokemon.type)
            )
        }
    }
}
