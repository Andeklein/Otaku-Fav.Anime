package com.andre.otakufav_anime.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andre.otakufav_anime.data.model.Ani
import com.andre.otakufav_anime.databinding.AItemLayoutBinding

class AniAdapter(
    private val aniList: List<Ani>
) : RecyclerView.Adapter<AniAdapter.AViewHolder>() {

    class AViewHolder(val binding: AItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AViewHolder {
        val binding = AItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AViewHolder, position: Int) {
        val aItem = aniList[position]
        holder.binding.aTextView.text = aItem.name
        holder.binding.aDescriptionTextView.text = aItem.description
    }

    override fun getItemCount(): Int {
        return aniList.size
    }
}