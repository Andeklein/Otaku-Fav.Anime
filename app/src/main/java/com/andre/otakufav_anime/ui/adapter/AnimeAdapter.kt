package com.example.animeapp.ui.exploreanime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andre.otakufav_anime.R
import com.example.animeapp.data.model.Anime

class AnimeAdapter(
    private var animeList: List<Anime>,
    private val onLikeClick: (Anime) -> Unit,
    private val onDeleteClick: (Anime) -> Unit

) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_explore_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {

        val anime = animeList[position]
        holder.bind(anime)
    }

    override fun getItemCount(): Int = animeList.size

    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val animeImage: ImageView = itemView.findViewById(R.id.iv_charakter)
        private val animeTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val likeButton: ImageButton = itemView.findViewById(R.id.ivHeartIcon_charakter)
        private val trashButton: ImageButton = itemView.findViewById(R.id.iv_trash_charakter)
        private val spinner: View = itemView.findViewById(R.id.spinner)

        fun bind(anime: Anime) {

            animeTitle.text = anime.anime
            animeImage.load(anime.image)
            animeInfo.text = anime.info
            animeGenre.text = anime.genre.toString()

            likeButton.setOnClickListener { onLikeClick(anime) }
            trashButton.setOnClickListener { onDeleteClick(anime) }
        }
    }
}