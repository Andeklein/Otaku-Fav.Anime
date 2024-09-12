package com.example.animeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andre.otakufav_anime.databinding.GenreItemLayoutBinding

class GenreAdapter(
    private val genres: List<String>
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    // ViewHolder für das Item-Layout
    class GenreViewHolder(val binding: GenreItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // Erstellt ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = GenreItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    // Bindet Daten an das ViewHolder-Item
    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.genreTextView.text = genres[position]
    }

    // Gibt die Anzahl der Elemente zurück
    override fun getItemCount(): Int {
        return genres.size
    }
}