package com.example.vk_intern_mobile.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vk_intern_mobile.R
import com.example.vk_intern_mobile.core.messages.dto.PokemonDto
import com.example.vk_intern_mobile.core.messages.dto.PokemonForRecycler

class PokemonsAdapter(
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<PokemonsAdapter.PokemonViewHolder>() {
    private val pokemonsList: MutableList<PokemonForRecycler> = mutableListOf()

    fun setPokemons(pokemons: List<PokemonForRecycler>) {
        pokemonsList.clear()
        pokemonsList.addAll(pokemons)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vh_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonsList[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(pokemonsList[position])
        }
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvPokeName: TextView = itemView.findViewById(R.id.tvPokeName)
        private val img: ImageView = itemView.findViewById(R.id.avatar)
        fun bind(data: PokemonForRecycler) {
            tvPokeName.text = data.name
            Glide.with(img).load(data.imgUrl).into(img)
        }
    }

    interface OnRecyclerItemClicked {
        fun onClick(pokemon: PokemonForRecycler)
    }
}