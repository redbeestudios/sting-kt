package com.redbee.pokemonapi.adapter.rest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.redbee.pokemonapi.domain.Ability

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class AbilityVO(
        val name: String,
        val effectEntries: List<EffectEntriesVO>? = null
) {

    fun toAbilityDomain(): Ability {
        return Ability(
                name = name,
                description = effectEntries?.get(0)?.effect
        )
    }
}
