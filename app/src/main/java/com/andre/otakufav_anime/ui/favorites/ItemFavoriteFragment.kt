package com.andre.otakufav_anime.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andre.otakufav_anime.databinding.FragmentItemFavoriteBinding

class ItemFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentItemFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
}