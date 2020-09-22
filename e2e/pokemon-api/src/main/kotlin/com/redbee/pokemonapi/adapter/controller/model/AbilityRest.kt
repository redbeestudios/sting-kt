package com.redbee.pokemonapi.adapter.controller.model

import com.redbee.pokemonapi.domain.Ability
import java.math.BigDecimal

data class AbilityRest(
        val name: String?,
        val description: String? = null,
        val damage: BigDecimal? = null
) {

    companion object {
        fun toAbilityRest(ability: Ability?): AbilityRest {
            return AbilityRest(
                    name = ability?.name,
                    description = ability?.description,
                    damage = ability?.damage
            )
        }
    }
}
