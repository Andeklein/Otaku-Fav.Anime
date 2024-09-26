package com.andre.otakufav_anime.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andre.otakufav_anime.utils.Utils
import com.andre.otakufav_anime.data.remote.Character
import com.andre.otakufav_anime.databinding.ItemCharakterBinding

class CharakterAdapter(

    private val charakterList: List<Character>

) : RecyclerView.Adapter<CharakterAdapter.CharakterViewHolder>() {

    inner class CharakterViewHolder(val binding: ItemCharakterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharakterAdapter.CharakterViewHolder {
        val binding =
            ItemCharakterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharakterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharakterViewHolder, position: Int) {
        var character = charakterList[position]
        val newUrl = Utils.extractImageUrl(character.image)

            holder.binding.tvCharacterName.text = character.name
            holder.binding.tvCharacterDescription.text = character.description
            holder.binding.tvCharacterFHigkeiten.text = character.fähigkeiten.joinToString(", ")
            holder.binding.ivCharacterImage.load(newUrl)

    }

    override fun getItemCount(): Int {
        return charakterList.size
    }

}