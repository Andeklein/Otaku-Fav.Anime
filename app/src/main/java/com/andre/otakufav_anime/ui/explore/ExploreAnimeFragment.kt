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
import com.andre.otakufav_anime.viewModel.MainViewModel
import com.andre.otakufav_anime.utils.Utils
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

        setUpSpinner()

        binding.ivInfo.setOnClickListener {
            val action =
                ExploreAnimeFragmentDirections.actionExploreAnimeFragmentToDetailAnimeFragment()
            findNavController().navigate(action)
        }

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

        viewModel.randomAnime.observe(viewLifecycleOwner) {
            val newUrl = Utils.extractImageUrl(it.image)
            Log.d("Anime", "observe: $it")
            binding.tvTitle.text = it.anime
            binding.ivAnime.load(newUrl)
            Log.d("AnimeImage", "observe: ${newUrl}")
        }

        setUpSpinner()
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
                            // Navigate to ExploreCharakterFragment if "Charakter" is selected
                            val action =
                                ExploreAnimeFragmentDirections.actionExploreAnimeFragmentToExploreCharakterFragment()
                            findNavController().navigate(action)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No action needed
                }
            }
    }
}