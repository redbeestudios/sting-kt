package com.redbee.pokemonapi.domain

data class Page<T>(
        val size: Int,
        val page: Int,
        val totalPages: Int,
        val elements: List<T>
)
