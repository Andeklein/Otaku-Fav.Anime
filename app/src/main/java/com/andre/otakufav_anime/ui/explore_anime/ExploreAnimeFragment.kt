package com.andre.otakufav_anime.ui.explore_anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.andre.otakufav_anime.R
import com.andre.otakufav_anime.data.remote.AnimeApiService
import com.andre.otakufav_anime.databinding.FragmentExploreAnimeBinding
import com.example.animeapp.ui.exploreanime.AnimeAdapter

class ExploreAnimeFragment : Fragment() {

    private lateinit var binding: FragmentExploreAnimeBinding
    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var animeApiService: AnimeApiService

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

        setUpRecyclerView()
        setUpSpinner()
        loadAnimesFromApi()
    }

    private fun setUpRecyclerView() {

        animeAdapter = AnimeAdapter(onLikeClick = { anime ->
            addAnimeToFavorites(anime) {

            }


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


                private fun loadAnimesFromApi() {
            animeApiService.getAnimeList()

        }

    }
}