package com.example.vk_intern_mobile.data

import com.example.vk_intern_mobile.core.messages.AbilityInfoItem
import com.example.vk_intern_mobile.core.messages.PokemonItem
import com.example.vk_intern_mobile.core.messages.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ) : PokemonItem

    @GET("ability/{name}")
    suspend fun getAbilityByName(
        @Path("name") name: String
    ) : AbilityInfoItem
}