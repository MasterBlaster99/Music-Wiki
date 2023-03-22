package com.example.musicwiki.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.musicwiki.MyApp
import com.example.musicwiki.R
import com.example.musicwiki.adapters.GenresAdapter
import com.example.musicwiki.databinding.FragmentAlbumDetailsBinding
import com.example.musicwiki.viewmodel.GenreViewModelFactory
import com.example.musicwiki.viewmodel.GenresViewModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject


class AlbumDetailsFragment : Fragment(), GenresAdapter.OnClickListener {

    private lateinit var binding: FragmentAlbumDetailsBinding
    private val args = navArgs<AlbumDetailsFragmentArgs>()
    private lateinit var viewModel: GenresViewModel
    private lateinit var adapter : GenresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumDetailsBinding.inflate(layoutInflater)
        binding.toolbar2.ivBack.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        viewModel = ViewModelProvider(
            this,
            GenreViewModelFactory((requireActivity().application as MyApp).repository)
        )[GenresViewModel::class.java]
        adapter = GenresAdapter(requireContext(),this)
        binding.rvGenres.adapter = adapter
        viewModel.album.observe(requireActivity()) {
            if (it != null) {
                binding.toolbar2.title.text =
                    it.asJsonArray.get(args.value.position).asJsonObject.get("name").toString()
                        .replace("\"","")
                context?.let { it1 ->
                    Glide.with(it1).load(
                        it.asJsonArray.get(args.value.position).asJsonObject.get("image").asJsonArray.get(2).asJsonObject.get("#text").toString()
                            .replace("\"", "")).placeholder(R.drawable.ic_album).into(binding.imageViewPager)
                }
                binding.tvArtistName.text =
                    it.asJsonArray.get(args.value.position).asJsonObject.get("artist").asJsonObject.get("name").toString()
                        .replace("\"","")
                viewModel.getAllGenres("https://ws.audioscrobbler.com/2.0/?method=album.gettoptags&artist=${it.asJsonArray.get(args.value.position).asJsonObject.get("artist").asJsonObject.get("name").toString()
                    .replace("\"","")}&album=${it.asJsonArray.get(args.value.position).asJsonObject.get("name").toString()
                    .replace("\"","")}&api_key=ae06783a7d927f4b558c5f7d4041d270&format=json")
            }
        }
        viewModel.genres.observe(requireActivity()){
            adapter.submitList(it.toList() as ArrayList<JsonObject>)
        }

        return binding.root
    }

    override fun onClick(genre: JsonObject, position: Int) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set("genre", genre.get("name").toString().replace("\"",""))
        findNavController().popBackStack()
    }


}