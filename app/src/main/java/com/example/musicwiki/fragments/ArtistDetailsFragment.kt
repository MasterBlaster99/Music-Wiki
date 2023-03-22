package com.example.musicwiki.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.musicwiki.MyApp
import com.example.musicwiki.R
import com.example.musicwiki.databinding.FragmentArtistDetailsBinding
import com.example.musicwiki.viewmodel.GenreViewModelFactory
import com.example.musicwiki.viewmodel.GenresViewModel

class ArtistDetailsFragment : Fragment(R.layout.fragment_artist_details) {

    private lateinit var binding: FragmentArtistDetailsBinding
    private lateinit var viewModel: GenresViewModel
    private val args = navArgs<ArtistDetailsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtistDetailsBinding.inflate(layoutInflater)
        binding.toolbar.ivBack.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        viewModel = ViewModelProvider(
            this,
            GenreViewModelFactory((requireActivity().application as MyApp).repository)
        )[GenresViewModel::class.java]
        viewModel.getArtistInfo(args.value.artistName)

        viewModel.artistInfo.observe(requireActivity()){

        }
        return binding.root
    }

}