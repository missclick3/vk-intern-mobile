package com.example.vk_intern_mobile.core.messages

import com.example.vk_intern_mobile.core.messages.util_responses.SpritesResponse
import kotlinx.serialization.Serializable

@Serializable
data class PokemonItem(
    val name: String,
    val id: Int,
    val abilities: List<AbilityItem>,
    val sprites: SpritesResponse,//Картинка
    val height: Int,
    val stats: List<StatItem>,
    val weight: Int
)
