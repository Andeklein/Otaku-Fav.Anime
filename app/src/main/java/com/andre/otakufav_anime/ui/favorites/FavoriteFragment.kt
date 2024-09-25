package com.andre.otakufav_anime.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andre.otakufav_anime.MainViewModel
import com.andre.otakufav_anime.R
import com.andre.otakufav_anime.data.remote.AnimeApiResponse
import com.andre.otakufav_anime.databinding.FragmentFavoriteBinding
import com.andre.otakufav_anime.ui.adapter.AnimeAdapter

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadLikedAnimes()

        viewModel.isLikedAnime.observe(viewLifecycleOwner) { likedAnimes ->

            val adapter = AnimeAdapter(likedAnimes)
            binding.rvFavorite.adapter = adapter
        }
    }
}
