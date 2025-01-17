package com.andre.otakufav_anime.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.andre.otakufav_anime.databinding.FragmentDetailExploreCharakterBinding
import com.andre.otakufav_anime.utils.Utils
import com.andre.otakufav_anime.viewModel.MainViewModel

class DetailExploreCharakterFragment : Fragment() {

    private lateinit var binding: FragmentDetailExploreCharakterBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailExploreCharakterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentCharacter.observe(viewLifecycleOwner) {

                    val newCharacter = it
                    val newUrl = Utils.extractImageUrl(newCharacter.image)
                    binding.characterName.text = newCharacter.name
                    binding.characterImage.load(newUrl)
                    binding.characterDescription.text = newCharacter.description

        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}