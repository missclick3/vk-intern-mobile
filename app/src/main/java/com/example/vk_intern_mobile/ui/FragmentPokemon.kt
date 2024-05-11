package com.example.vk_intern_mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.vk_intern_mobile.R
import com.example.vk_intern_mobile.databinding.FragmentPokemonBinding
import com.example.vk_intern_mobile.presentation.PokemonResult
import com.example.vk_intern_mobile.presentation.PokemonUIEvent
import com.example.vk_intern_mobile.presentation.PokemonViewModel
import com.example.vk_intern_mobile.presentation.adapters.AbilitiesAdapter
import com.example.vk_intern_mobile.presentation.adapters.StatsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentPokemon : Fragment(R.layout.fragment_pokemon) {

    private val viewModel: PokemonViewModel by viewModels()
    private lateinit var binding: FragmentPokemonBinding
    private val statsAdapter: StatsAdapter by lazy { StatsAdapter() }
    private val abilitiesAdapter: AbilitiesAdapter by lazy { AbilitiesAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerStats.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerStats.adapter = statsAdapter
        binding.recyclerAbilities.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerAbilities.adapter = abilitiesAdapter
        val pokeName = arguments?.getString("name")!!
        binding.tvName.text = pokeName
        viewModel.onEvent(PokemonUIEvent.PokemonClicked(pokeName))
        setupChannelResults()
    }

    private fun setupChannelResults() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.pokemonResult.collect { result ->
                when (result) {
                    is PokemonResult.Error -> {
                        binding.clInfo.isVisible = true
                        binding.pokeLoader.isVisible = false
                        Toast.makeText(requireContext(), result.errorText, Toast.LENGTH_LONG).show()
                    }
                    PokemonResult.Loading -> {
                        binding.clInfo.isVisible = false
                        binding.pokeLoader.isVisible = true
                    }
                    is PokemonResult.Pokemon -> {
                        binding.clInfo.isVisible = true
                        binding.pokeLoader.isVisible = false
                        Glide.with(binding.image).load(result.item.imageUrl).into(binding.image)
                        binding.height.text = "height: ${(result.item.height * 10).toString()} cm"
                        binding.weight.text = "weight: ${(result.item.weight.toDouble() / 10).toString()} kg"
                        statsAdapter.setStats(result.item.stats)
                        abilitiesAdapter.setAbilities(result.item.abilities)
                    }
                    is PokemonResult.PokemonsList -> {}
                }
            }
        }
    }
}