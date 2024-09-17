package com.andre.otakufav_anime.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andre.otakufav_anime.R
import com.andre.otakufav_anime.databinding.FragmentDetailAnimeBinding
import com.andre.otakufav_anime.databinding.FragmentDetailExploreCharakterBinding

class DetailAnimeFragment : Fragment() {

    private var _binding: FragmentDetailAnimeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            val action = DetailAnimeFragmentDirections.actionDetailAnimeFragmentToExploreAnimeFragment()
            findNavController().navigateUp()
        }

    }

}