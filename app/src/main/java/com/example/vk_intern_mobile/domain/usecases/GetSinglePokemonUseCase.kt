package com.example.vk_intern_mobile.domain.usecases

import android.util.Log
import com.example.vk_intern_mobile.core.messages.AbilityInfoItem
import com.example.vk_intern_mobile.core.messages.StatItem
import com.example.vk_intern_mobile.core.messages.dto.AbilityDto
import com.example.vk_intern_mobile.core.messages.dto.PokemonDto
import com.example.vk_intern_mobile.core.messages.dto.StatDto
import com.example.vk_intern_mobile.domain.repositories.PokemonRepository
import com.example.vk_intern_mobile.presentation.PokemonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSinglePokemonUseCase(
    private val repository: PokemonRepository
) {
    suspend fun execute(name: String) : PokemonResult {
        return try {
            val firstResponse = withContext(Dispatchers.IO) {
                repository.getPokemon(name)
            }
            val secondResponse = firstResponse.abilities.map { ability ->
                val info = withContext(Dispatchers.IO) {
                    repository.getAbility(ability.ability.name)
                }
                AbilityDto(
                    name = info.name,
                    description = info.effect_entries.getOrNull(1)?.short_effect ?: "No description"
                )
            }
            PokemonResult.Pokemon(
                PokemonDto(
                    name = firstResponse.name,
                    abilities = secondResponse,
                    imageUrl = firstResponse.sprites.front_default,
                    height = firstResponse.height,
                    stats = firstResponse.stats.map(::statItemToStatDto),
                    weight = firstResponse.weight
                )
            )
        } catch (e: Exception) {
            PokemonResult.Error(e.message ?: "Unknown Error")
        }
    }

    private fun statItemToStatDto(item: StatItem) : StatDto {
        return StatDto(
            name = item.stat.name,
            baseStat = item.base_stat
        )
    }
}