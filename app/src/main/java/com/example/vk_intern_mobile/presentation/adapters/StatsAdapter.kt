package com.example.vk_intern_mobile.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_intern_mobile.R
import com.example.vk_intern_mobile.core.messages.dto.StatDto

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {
    private val list: MutableList<StatDto> = mutableListOf()

    fun setStats(stats: List<StatDto>) {
        list.clear()
        list.addAll(stats)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatsAdapter.StatsViewHolder {
        return StatsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vh_stat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StatsAdapter.StatsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvStat: TextView = itemView.findViewById(R.id.tvStat)
        fun bind(data: StatDto) {
            tvStat.text = "${data.name}: ${data.baseStat}"
        }
    }

}