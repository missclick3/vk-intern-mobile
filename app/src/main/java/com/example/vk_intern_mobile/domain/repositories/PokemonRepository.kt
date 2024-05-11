package com.example.vk_intern_mobile.domain.repositories

import com.example.vk_intern_mobile.core.messages.AbilityInfoItem
import com.example.vk_intern_mobile.core.messages.dto.PokemonForRecycler
import com.example.vk_intern_mobile.core.messages.PokemonItem

interface PokemonRepository {
    suspend fun getPokemons() : List<PokemonForRecycler>
    suspend fun getPokemon(name: String) : PokemonItem
    suspend fun getAbility(name: String) : AbilityInfoItem
}