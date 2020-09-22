package com.redbee.pokemonapi.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal


@DisplayName("Pokemon Domain Test")
class PokemonTest {

    @Test
    @DisplayName("When a pokemon attack then should return a remaining health to the target")
    fun testPokemonAttack() {
        //given
        val myPokemon = buildPokemon()

        //when
        myPokemon.attack(BigDecimal.valueOf(100))

        //then
        assertEquals(BigDecimal.valueOf(400), myPokemon.health)
    }

    private fun buildPokemon(): Pokemon {
        return Pokemon(name = "pikachu", health = BigDecimal.valueOf(500))
    }
}
