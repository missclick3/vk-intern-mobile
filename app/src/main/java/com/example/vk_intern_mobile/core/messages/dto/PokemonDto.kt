package com.example.vk_intern_mobile.core.messages.dto

import com.example.vk_intern_mobile.core.messages.StatItem

data class PokemonDto(
    val name: String,
    val abilities: List<AbilityDto>,
    val imageUrl: String,//Картинка
    val height: Int,
    val weight: Int,
    val stats: List<StatDto>
)