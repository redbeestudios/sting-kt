package com.redbee.pokemonapi.application.port.out

import com.redbee.pokemonapi.domain.Ability

interface AbilityRepository {
    fun getAbility(name: String): Ability
}
