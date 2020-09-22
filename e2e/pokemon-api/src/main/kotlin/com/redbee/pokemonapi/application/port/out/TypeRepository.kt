package com.redbee.pokemonapi.application.port.out

import com.redbee.pokemonapi.domain.Type

interface TypeRepository {
    fun getType(typeName: String?): Type
}
