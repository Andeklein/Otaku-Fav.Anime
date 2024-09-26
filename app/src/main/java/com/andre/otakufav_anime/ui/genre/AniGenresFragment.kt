package com.andre.otakufav_anime.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andre.otakufav_anime.R
import com.andre.otakufav_anime.databinding.FragmentAniGenresBinding
import com.example.animeapp.A
import com.example.animeapp.AAdapter
import com.andre.otakufav_anime.viewModel.AGenresViewModel

class AniGenresFragment : Fragment() {

    private var _binding: FragmentAniGenresBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AGenresViewModel by viewModels()

    private lateinit var aAdapter: AAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAniGenresBinding.inflate(inflater, container, false)

        viewModel.genres.observe(viewLifecycleOwner) { genres ->
            setupSpinner(genres)
        }

        return binding.root
    }

    private fun setupSpinner(genres: List<String>) {

        val banners = mapOf(
            "Action" to R.drawable.action_genre,
            "Adventure" to R.drawable.adventure_genre,
            "Comedy" to R.drawable.comedy_genre,
            "Cyberpunk" to R.drawable.cyberpunk_genre,
            "Demons" to R.drawable.demons_genre,
            "Drama" to R.drawable.drama_genre,
            "Ecchi" to R.drawable.ecchi_genre,
            "Fantasy" to R.drawable.fantasy_genre,
            "Game" to R.drawable.game_genre,
            "Harem" to R.drawable.harem_genre,
            "Historical" to R.drawable.historical_genre,
            "Horror" to R.drawable.horror_genre,
            "Isekai" to R.drawable.isekai_genre,
            "Josei" to R.drawable.josei_genre,
            "Martial Arts" to R.drawable.martial_arts_genre,
            "Mecha" to R.drawable.mecha_genre,
            "Military" to R.drawable.military_genre,
            "Music" to R.drawable.music_genre,
            "Mystery" to R.drawable.mystery_genre,
            "Psychological" to R.drawable.psychological_genre,
            "Romance" to R.drawable.romance_genre,
            "School" to R.drawable.school_genre,
            "Sci-Fi" to R.drawable.sci_fi_genre,
            "Seinen" to R.drawable.seinen_genre,
            "Shoujo" to R.drawable.shoujo_genre,
            "Shounen" to R.drawable.shounen_genre,
            "Slice of Life" to R.drawable.slice_of_life_genre,
            "Sports" to R.drawable.sports_genre,
            "Supernatural" to R.drawable.supernatural_genre,
            "Thriller" to R.drawable.thriller_genre,
            "Yaoi" to R.drawable.yaoi_genre,
            "Yuri" to R.drawable.yuri_genre
        )

        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genres)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genreSpinner.adapter = spinnerAdapter

        binding.genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedGenre = genres[position]
                updateGenreDetails(selectedGenre,banners[selectedGenre] ?: R.drawable.action_genre)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun updateGenreDetails(genre: String,banner: Int) {
        binding.genreDescriptionTextView.text = viewModel.getGenreDescription(genre)
        binding.bannerImageView.setImageResource(banner)

        val aList = viewModel.getAForGenre(genre)
        setupARecyclerView(aList)
    }

    private fun setupARecyclerView(aList: List<A>) {
        aAdapter = AAdapter(aList)
        binding.recyclerViewGenres.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = aAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}