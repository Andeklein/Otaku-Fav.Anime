package com.andre.otakufav_anime.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andre.otakufav_anime.Utils
import com.andre.otakufav_anime.data.remote.AnimeApiResponse
import com.andre.otakufav_anime.databinding.FragmentItemFavoriteBinding

class AnimeAdapter(
    private val animeList: List<AnimeApiResponse>
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    inner class AnimeViewHolder(val binding: FragmentItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = FragmentItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        val newUrl = Utils.extractImageUrl(anime.image)

        holder.binding.tvAnimeTitle.text = anime.anime
        holder.binding.imgAnime.load(newUrl)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}