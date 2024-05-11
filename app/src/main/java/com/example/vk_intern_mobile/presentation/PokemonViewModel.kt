package com.example.vk_intern_mobile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_intern_mobile.domain.usecases.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val useCases: PokemonUseCases
) : ViewModel(){

    private val resultChannel = Channel<PokemonResult>()
    val pokemonResult = resultChannel.receiveAsFlow()

    fun onEvent(event: PokemonUIEvent) {
        when(event) {
            PokemonUIEvent.InitPokemonList -> getAllPokemons()
            is PokemonUIEvent.PokemonClicked -> getPokemon(event.name)
        }
    }

    private fun getAllPokemons() {
        viewModelScope.launch {
            resultChannel.send(PokemonResult.Loading)
            val result = useCases.getAllPokemonsUseCase.execute()
            resultChannel.send(result)
        }
    }

    private fun getPokemon(name: String) {
        viewModelScope.launch {
            resultChannel.send(PokemonResult.Loading)
            val result = useCases.getSinglePokemonUseCase.execute(name)
            resultChannel.send(result)
        }
    }
}