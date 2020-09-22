package com.redbee.pokemonapi.adapter.rest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.redbee.pokemonapi.domain.Ability

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class AbilitiesVO(
        val ability: AbilityVO
) {

    fun toAbilitiesDomain(): Ability {
        return Ability(name = ability.name)
    }
}
