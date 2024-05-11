package com.example.vk_intern_mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vk_intern_mobile.R
import com.example.vk_intern_mobile.core.messages.dto.PokemonForRecycler
import com.example.vk_intern_mobile.databinding.FragmentPokemonsBinding
import com.example.vk_intern_mobile.presentation.PokemonResult
import com.example.vk_intern_mobile.presentation.PokemonUIEvent
import com.example.vk_intern_mobile.presentation.PokemonViewModel
import com.example.vk_intern_mobile.presentation.adapters.PokemonsAdapter

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentPokemons : Fragment(R.layout.fragment_pokemons) {
    private val adapter: PokemonsAdapter by lazy { PokemonsAdapter(clickListener) }
    private val viewModel: PokemonViewModel by viewModels()
    private lateinit var binding: FragmentPokemonsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerPokemons.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerPokemons.adapter = adapter
        viewModel.onEvent(PokemonUIEvent.InitPokemonList)
        setupChannelResults()
    }

    private fun setupChannelResults() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.pokemonResult.collect {result ->
                when(result) {
                    is PokemonResult.Error -> {
                        binding.loader.isVisible = false
                        binding.recyclerPokemons.isVisible = true
                        Toast.makeText(requireContext(), result.errorText, Toast.LENGTH_LONG).show()
                    }
                    PokemonResult.Loading -> {
                        binding.loader.isVisible = true
                        binding.recyclerPokemons.isVisible = false
                    }
                    is PokemonResult.Pokemon -> {}
                    is PokemonResult.PokemonsList -> {
                        binding.loader.isVisible = false
                        binding.recyclerPokemons.isVisible = true
                        adapter.setPokemons(result.items)
                    }
                }
            }
        }
    }

    private fun goToPokemonFragment(name: String) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val pokemonFragment = FragmentPokemon()
        val bundle = Bundle()
        bundle.putString("name", name)
        pokemonFragment.arguments = bundle
        fragmentTransaction.replace(R.id.frame_layout, pokemonFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun doOnClick(name: String) {
        binding.recyclerPokemons.let { rv ->
            goToPokemonFragment(name)
        }
    }

    private val clickListener = object : PokemonsAdapter.OnRecyclerItemClicked {
        override fun onClick(pokemon: PokemonForRecycler) {
            doOnClick(pokemon.name)
        }
    }
}