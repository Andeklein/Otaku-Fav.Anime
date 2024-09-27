package com.andre.otakufav_anime.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andre.otakufav_anime.utils.Utils
import com.andre.otakufav_anime.data.remote.CharacterRoom
import com.andre.otakufav_anime.databinding.ItemCharakterBinding
import com.andre.otakufav_anime.viewModel.MainViewModel

class CharakterAdapter(

    private val charakterList: List<CharacterRoom>,
    private val viewModel: MainViewModel 

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
            holder.binding.tvCharacterFHigkeiten.text = character.faehigkeiten.joinToString(", ")
            holder.binding.ivCharacterImage.load(newUrl)

        viewModel.setCurrentCharacter(character)
    }

    override fun getItemCount(): Int {
        return charakterList.size
    }

}