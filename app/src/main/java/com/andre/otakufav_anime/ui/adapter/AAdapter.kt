package com.example.animeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andre.otakufav_anime.databinding.AItemLayoutBinding

class AAdapter(
    private val aList: List<A>
) : RecyclerView.Adapter<AAdapter.AViewHolder>() {

    class AViewHolder(val binding: AItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AViewHolder {
        val binding = AItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AViewHolder, position: Int) {
        val aItem = aList[position]
        holder.binding.aTextView.text = aItem.name
        holder.binding.aDescriptionTextView.text = aItem.description
    }

    override fun getItemCount(): Int {
        return aList.size
    }
}