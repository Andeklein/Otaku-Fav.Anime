package com.andre.otakufav_anime.ui.explore_anime

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.andre.otakufav_anime.MainViewModel
import com.andre.otakufav_anime.data.model.IsLikedAnime
import com.andre.otakufav_anime.databinding.FragmentExploreAnimeBinding

class ExploreAnimeFragment : Fragment() {

    private lateinit var binding: FragmentExploreAnimeBinding
    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivInfo.setOnClickListener {
            val action = ExploreAnimeFragmentDirections.actionExploreAnimeFragmentToDetailAnimeFragment()
            findNavController().navigate(action)
        }

        binding.ivPopcorn.setOnClickListener {
            val action = ExploreAnimeFragmentDirections.actionExploreAnimeFragmentToYouTubeWebViewFragment()
            findNavController().navigate(action)
        }

        binding.ivHeartIcon.setOnClickListener {
            var isLikedAnime = IsLikedAnime(0,"AnimeLiked")
            viewModel.insertIsLikedAnime(isLikedAnime)

        }


        viewModel.anime.observe(viewLifecycleOwner) {
            Log.d("Anime","observe: $it")
        }

        setUpSpinner()
    }

    private fun setUpSpinner() {

        val filterData = mutableListOf(
            "Anime",
            "Charakter"
        )

        binding.spinnerExplore.adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            filterData
        )

        binding.spinnerExplore.onItemSelectedListener

    }

}
