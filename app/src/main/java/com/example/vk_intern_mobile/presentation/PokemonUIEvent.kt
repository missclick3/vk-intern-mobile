package com.example.vk_intern_mobile.presentation

sealed class PokemonUIEvent {
    data object InitPokemonList : PokemonUIEvent()
    data class PokemonClicked(val name: String) : PokemonUIEvent()
}