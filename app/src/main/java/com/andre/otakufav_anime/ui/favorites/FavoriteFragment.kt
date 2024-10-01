package com.andre.otakufav_anime.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.andre.otakufav_anime.viewModel.MainViewModel
import com.andre.otakufav_anime.databinding.FragmentFavoriteBinding
import com.andre.otakufav_anime.ui.adapter.AnimeAdapter

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: MainViewModel by activityViewModels()

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

            val adapter = AnimeAdapter(likedAnimes,viewModel)
            binding.rvFavorite.adapter = adapter
        }
        setUpSpinner()
    }
    private fun setUpSpinner() {

        val filterData = mutableListOf(
            "Anime",
            "Character"
        )

        binding.spinner.adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            filterData
        )

        binding.spinner.onItemSelectedListener =
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
                                FavoriteFragmentDirections.actionFavoriteFragmentToFavoriteCharacterFragment()
                            findNavController().navigate(action)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }
}
