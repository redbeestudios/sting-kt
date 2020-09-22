package com.redbee.pokemonapi.application.usecase

import com.redbee.pokemonapi.application.port.`in`.FindPokemonAbilityInPort
import com.redbee.pokemonapi.application.port.out.AbilityRepository
import com.redbee.pokemonapi.application.port.out.PokemonRepository
import com.redbee.pokemonapi.application.port.out.TypeRepository
import com.redbee.pokemonapi.domain.Ability
import com.redbee.pokemonapi.domain.Pokemon
import com.redbee.pokemonapi.domain.Type
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GetPokemonAbilityUseCase(
    private val pokemonRepository: PokemonRepository,
    private val abilityRepository: AbilityRepository,
    private val typeRepository: TypeRepository
) : FindPokemonAbilityInPort {

    private val log = LoggerFactory.getLogger(GetPokemonAbilityUseCase::class.java)

    override fun by(name: String): Pokemon {
        val pokemon: Pokemon = pokemonRepository.getPokemon(name)
        log.info("Pokemon: {}", pokemon)

        val ability: Ability = abilityRepository.getAbility(pokemon.ability!!.name)
        val type: Type = typeRepository.getType(pokemon.type?.name)

        return Pokemon(
                ability = ability,
                type = type,
                name = pokemon.name
        )
    }

}
