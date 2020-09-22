package com.redbee.pokemonapi.adapter.controller.model

import com.redbee.pokemonapi.domain.Type

data class TypeRest(
        val name: String?,
        val moveDamageClass: String? = null
) {

    companion object {
        fun toTypeRest(type: Type?): TypeRest {
            return TypeRest(
                    name = type?.name,
                    moveDamageClass = type?.moveDamageClass)
        }
    }
}
