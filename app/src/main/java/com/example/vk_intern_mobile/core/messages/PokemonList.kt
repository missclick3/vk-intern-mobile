package com.example.vk_intern_mobile.core.messages

import com.example.vk_intern_mobile.core.messages.util_responses.PokemonResponse
import kotlinx.serialization.Serializable

@Serializable
data class PokemonList(
    val results: List<PokemonResponse>
)
