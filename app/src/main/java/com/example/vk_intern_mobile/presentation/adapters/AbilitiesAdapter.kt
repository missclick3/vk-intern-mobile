package com.example.vk_intern_mobile.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_intern_mobile.R
import com.example.vk_intern_mobile.core.messages.dto.AbilityDto
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class AbilitiesAdapter : RecyclerView.Adapter<AbilitiesAdapter.AbilitiesViewHolder>() {
    private val list: MutableList<AbilityDto> = mutableListOf()

    fun setAbilities(abilityList: List<AbilityDto>) {
        list.clear()
        list.addAll(abilityList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbilitiesAdapter.AbilitiesViewHolder {
        return AbilitiesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vh_ability, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AbilitiesAdapter.AbilitiesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class AbilitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAbilityName: Button = itemView.findViewById(R.id.tvAbilityName)
        private val tvAbilityDescription: TextView = itemView.findViewById(R.id.tvAbilityDescription)
        fun bind(data: AbilityDto) {
            tvAbilityName.text = data.name
            tvAbilityName.setOnClickListener {
                tvAbilityDescription.isVisible = !tvAbilityDescription.isVisible
            }
            tvAbilityDescription.text = data.description
        }
    }
}