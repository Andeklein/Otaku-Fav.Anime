package com.andre.otakufav_anime.ui.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.andre.otakufav_anime.R
import com.andre.otakufav_anime.viewModel.MainViewModel
import com.andre.otakufav_anime.utils.Utils
import com.andre.otakufav_anime.databinding.FragmentExploreAnimeBinding

class ExploreAnimeFragment : Fragment() {

    private lateinit var binding: FragmentExploreAnimeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSpinner()

        binding.ivPopcorn.setOnClickListener {
            val action =
                ExploreAnimeFragmentDirections.actionExploreAnimeFragmentToYouTubeWebViewFragment()
            findNavController().navigate(action)
        }

        binding.ivHeartIcon.setOnClickListener {
            viewModel.updateIsLikedAnime()
        }

        binding.ivTrashCharakter.setOnClickListener {
            viewModel.trashAnime()
        }

        viewModel.randomAnime.observe(viewLifecycleOwner)
         { anime->
            val newUrl = Utils.extractImageUrl(anime.image)
            Log.d("Anime", "observe: $anime")
            binding.tvTitle.text = anime.anime
            binding.ivAnime.load(newUrl)
            Log.d("AnimeImage", "observe: ${newUrl}")

            binding.ivInfo.setOnClickListener {
                viewModel.setCurrentAnime(anime)
                findNavController().navigate(R.id.action_exploreAnimeFragment_to_detailAnimeFragment)
            }
        }
    }

    private fun setUpSpinner() {

        val filterData = mutableListOf(
            "Anime",
            "Character"
        )

        binding.spinnerExplore.adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            filterData
        )

        binding.spinnerExplore.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        1 -> {
                            val action =
                                ExploreAnimeFragmentDirections.actionExploreAnimeFragmentToExploreCharakterFragment()
                            findNavController().navigate(action)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }
}
