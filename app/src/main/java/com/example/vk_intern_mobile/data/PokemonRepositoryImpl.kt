package com.example.vk_intern_mobile.data

import android.util.Log
import com.example.vk_intern_mobile.core.messages.AbilityInfoItem
import com.example.vk_intern_mobile.core.messages.dto.PokemonForRecycler
import com.example.vk_intern_mobile.core.messages.PokemonItem
import com.example.vk_intern_mobile.domain.repositories.PokemonRepository

class PokemonRepositoryImpl(
    private val api: PokemonApi
) : PokemonRepository {
    override suspend fun getPokemons(): List<PokemonForRecycler> {
        val pokemons = api.getPokemons(
            limit = 30,
            offset = 0
        ).results
        return pokemons.map {
            pokeItemToPokemonForRecycler(getPokemon(it.name))
        }
    }

    override suspend fun getPokemon(name: String): PokemonItem {
        return api.getPokemonByName(name)
    }

    override suspend fun getAbility(name: String): AbilityInfoItem {
        return api.getAbilityByName(name)
    }

    private fun pokeItemToPokemonForRecycler(item: PokemonItem) : PokemonForRecycler {
        return PokemonForRecycler(
            name = item.name,
            imgUrl = item.sprites.front_default
        )
    }
}