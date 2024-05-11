package com.example.vk_intern_mobile.domain.usecases

import android.util.Log
import com.example.vk_intern_mobile.domain.repositories.PokemonRepository
import com.example.vk_intern_mobile.presentation.PokemonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllPokemonsUseCase(
    private val repository: PokemonRepository
) {
    suspend fun execute() :PokemonResult {
        return try {
            val response = withContext(Dispatchers.IO) {
                repository.getPokemons()
            }
            PokemonResult.PokemonsList(items = response)
        } catch (e: Exception) {
            Log.e("HERE", e.message ?: "Unknown Error")
            PokemonResult.Error(e.message ?: "Unknown Error")
        }
    }
}