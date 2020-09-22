package com.redbee.pokemonapi.adapter.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.redbee.pokemonapi.adapter.controller.model.AbilityRest
import com.redbee.pokemonapi.adapter.controller.model.PokemonRest
import com.redbee.pokemonapi.adapter.controller.model.TypeRest
import com.redbee.pokemonapi.application.port.`in`.CreatePokemonInPort
import com.redbee.pokemonapi.application.port.`in`.FindPokemonAbilityInPort
import com.redbee.pokemonapi.config.ApiError
import com.redbee.pokemonapi.config.TestConfig
import com.redbee.pokemonapi.config.exception.ResourceNotFoundException
import com.redbee.pokemonapi.domain.Ability
import com.redbee.pokemonapi.domain.Pokemon
import com.redbee.pokemonapi.domain.Type
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@DisplayName("PokemonController Adapter Test")
@WebMvcTest(PokemonControllerAdapter::class)
@Import(value = [TestConfig::class])
class PokemonControllerAdapterTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Autowired
    private val objectMapper: ObjectMapper? = null

    @MockBean
    private val getPokemonAbilityQuery: FindPokemonAbilityInPort? = null

    @MockBean
    private val createPokemonCommand: CreatePokemonInPort? = null

    @Test
    @DisplayName("when the getPokemon is called, the adapter must return a pokemon")
    fun getPokemonOK() {
        `when`(getPokemonAbilityQuery!!.by(anyString())).thenReturn(pokemonDomain)
        mockMvc!!.perform(get("/v1/pokemon/{name}", PIKACHU))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().json(objectMapper!!.writeValueAsString(pokemonRestExpected)))
    }

    @Test
    @DisplayName("when the getPokemon is called, the adapter must return a NotFound exception")
    fun getPokemonNotFound() {
        `when`(getPokemonAbilityQuery!!.by(anyString()))
                .thenThrow(ResourceNotFoundException(ApiError.RESOURCE_NOT_FOUND.errorCode, ApiError.RESOURCE_NOT_FOUND.defaultMessage))
        mockMvc!!.perform(get("/v1/pokemon/{name}", PIKACHU))
                .andDo(print())
                .andExpect(status().isNotFound)
                .andExpect(content().string(containsString(ApiError.RESOURCE_NOT_FOUND.defaultMessage)))
    }


    private val pokemonDomain: Pokemon
        get() {
            val ability = Ability("thunder")
            val type = Type("normal")
            return Pokemon(PIKACHU, ability, type)
        }
    private val pokemonRestExpected: PokemonRest
        get() {
            val abilityView = AbilityRest("thunder")
            val typeView = TypeRest("normal")
            return PokemonRest(PIKACHU, abilityView, typeView)
        }

    companion object {
        private const val PIKACHU = "pikachu"
    }
}
