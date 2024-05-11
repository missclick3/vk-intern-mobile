package com.example.vk_intern_mobile.presentation

import com.example.vk_intern_mobile.core.messages.dto.PokemonForRecycler
import com.example.vk_intern_mobile.core.messages.PokemonItem
import com.example.vk_intern_mobile.core.messages.dto.PokemonDto

sealed class PokemonResult {
    data object Loading : PokemonResult()
    data class Error(val errorText: String) : PokemonResult()
    data class PokemonsList(val items: List<PokemonForRecycler>) : PokemonResult()
    data class Pokemon(val item: PokemonDto) : PokemonResult()
}