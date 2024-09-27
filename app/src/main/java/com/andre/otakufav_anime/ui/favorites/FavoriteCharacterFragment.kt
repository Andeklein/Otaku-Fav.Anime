package com.andre.otakufav_anime.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.andre.otakufav_anime.databinding.FragmentFavoriteCharacterBinding
import com.andre.otakufav_anime.ui.adapter.CharakterAdapter
import com.andre.otakufav_anime.viewModel.MainViewModel

class FavoriteCharacterFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteCharacterBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadLikedCharacters()

        viewModel.isLikedCharacter.observe(viewLifecycleOwner) { likedCharacters ->
            val adapter = CharakterAdapter(likedCharacters,viewModel)
            binding.rvFavoriteCharacter.adapter = adapter
        }

        setUpSpinner()
    }
    private fun setUpSpinner() {

        val filterData = mutableListOf(
            "Character" ,
            "Anime"
        )

        binding.spinnerCharacter.adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            filterData
        )

        binding.spinnerCharacter.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        1 -> {
                            // Navigate to FavoriteFragment if "Anime" is selected
                            val action =
                                FavoriteCharacterFragmentDirections.actionFavoriteCharacterFragmentToFavoriteFragment()
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