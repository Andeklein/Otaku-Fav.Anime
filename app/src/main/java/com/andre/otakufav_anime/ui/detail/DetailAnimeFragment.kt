package com.andre.otakufav_anime.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.andre.otakufav_anime.viewModel.MainViewModel
import com.andre.otakufav_anime.utils.Utils
import com.andre.otakufav_anime.databinding.FragmentDetailAnimeBinding
import com.andre.otakufav_anime.ui.adapter.CharakterAdapter

class DetailAnimeFragment : Fragment() {

    private var _binding: FragmentDetailAnimeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentAnime.observe(viewLifecycleOwner) {
            val newUrl = Utils.extractImageUrl(it.banner)
            val charakterAdapter = CharakterAdapter(it.characters)
            Log.d("Anime","observe: $it")
            binding.tvAnimeName.text = it.anime
            binding.ivAnimeBanner.load(newUrl)
            binding.tvAnimeDescription.text = it.info
            binding.tvGenre1.text = it.genre[0]
            binding.tvGenre2.text = it.genre[1]
            binding.tvGenre3.text = it.genre[2]
            binding.rvCharactersList.adapter = charakterAdapter

        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}