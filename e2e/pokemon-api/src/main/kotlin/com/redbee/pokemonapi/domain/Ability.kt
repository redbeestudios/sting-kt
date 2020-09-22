package com.redbee.pokemonapi.domain

import java.math.BigDecimal

data class Ability(
        val name: String,
        val description: String? = null,
        val damage: BigDecimal? = null
)
