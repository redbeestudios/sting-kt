package com.redbee.pokemonapi.config

enum class ApiError(val errorCode: Int, val defaultMessage: String) {

    INTERNAL_ERROR(100, "Error interno del servidor"),
    RESOURCE_NOT_FOUND(101, "No se encontro recurso solicitado"),
    TIMEOUT_FOUND(102, "Se demoro demaciado tratando de realizar la accion solicitada"),
    POKEMON_NOT_FOUND(101, "No se encontro el pokemon"),
    ABILITY_NOT_FOUND(102, "No se encontro la Habilidad del pokemon"),
    ABILITY_TIMEOUT(103, "El llamado a Ability devolvio error"),
    TYPE_NOT_FOUND(104, "No se encontro el Tipo del pokemon"),
    ACCESS_DENIED(106, "Access is denied"),
    INVALID_COMMAND(111, "Comando invalido")

}
