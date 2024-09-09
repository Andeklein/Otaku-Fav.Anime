package com.andre.otakufav_anime.ui.explore_anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.andre.otakufav_anime.R
import com.andre.otakufav_anime.databinding.FragmentExploreAnimeBinding

class ExploreAnimeFragment : Fragment() {

    lateinit var binding: FragmentExploreAnimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentExploreAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSpinner()
    }

    private fun setUpSpinner() {

        // Spinner zur auswahl für die type filterung
        val filterData = mutableListOf("Anime",
                                        "Charakter")

        binding.spinnerExplore.adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            filterData
        )

        binding.spinnerExplore.onItemSelectedListener

    }

}