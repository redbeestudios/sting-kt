package com.redbee.pokemonapi.adapter.rest.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.redbee.pokemonapi.domain.Type

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class TypesVO(
        val type: TypeVO
) {

    fun toTypesDomain(): Type {
        return Type(name = type.name)
    }
}
