package com.andre.otakufav_anime.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andre.otakufav_anime.R
import com.andre.otakufav_anime.utils.Utils
import com.andre.otakufav_anime.data.remote.AnimeApiResponse
import com.andre.otakufav_anime.databinding.FragmentItemFavoriteBinding
import com.andre.otakufav_anime.viewModel.MainViewModel

class AnimeAdapter(

    private val animeList: List<AnimeApiResponse>,
    private val viewModel: MainViewModel

) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    inner class AnimeViewHolder(val binding: FragmentItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding =
            FragmentItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        val newUrl = Utils.extractImageUrl(anime.image)

        holder.binding.tvAnimeTitle.text = anime.anime
        holder.binding.imgAnime.load(newUrl)
        holder.binding.cardview.setOnClickListener {

            viewModel.setCurrentAnime(anime)
            val navController = holder.itemView.findNavController()
            navController.navigate(R.id.detailAnimeFragment)
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}