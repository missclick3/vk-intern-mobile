package com.example.vk_intern_mobile.core.messages.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonForRecycler(
    val name: String,
    val imgUrl: String
)
