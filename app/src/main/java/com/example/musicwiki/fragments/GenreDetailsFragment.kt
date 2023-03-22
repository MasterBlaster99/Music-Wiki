package com.example.musicwiki.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.musicwiki.MyApp
import com.example.musicwiki.R
import com.example.musicwiki.adapters.TopItemsAdapter
import com.example.musicwiki.databinding.FragmentGenreDetailsBinding
import com.example.musicwiki.viewmodel.GenreViewModelFactory
import com.example.musicwiki.viewmodel.GenresViewModel
import com.google.gson.JsonObject


class GenreDetailsFragment : Fragment(R.layout.fragment_genre_details),
    TopItemsAdapter.OnClickListener {

    private lateinit var binding: FragmentGenreDetailsBinding
    private val args = navArgs<GenreDetailsFragmentArgs>()
    private lateinit var viewModel: GenresViewModel
    private lateinit var albumsAdapter: TopItemsAdapter
    private lateinit var artistsAdapter: TopItemsAdapter
    private var genreName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenreDetailsBinding.inflate(layoutInflater)
        genreName = args.value.genreName
        setupViews()
        setUpViewModel()
        setupObservers()
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("genre")?.observe(viewLifecycleOwner) { result ->
            genreName = result
            setupViews()
            setUpViewModel()
            setupObservers()
        }


        return binding.root
    }

    fun setupViews(){
        binding.toolbar.ivBack.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.toolbar.title.text = genreName
        albumsAdapter = TopItemsAdapter(requireContext(),this)
        artistsAdapter = TopItemsAdapter(requireContext(),this,true)
        binding.rvAlbums.adapter = albumsAdapter
        binding.rvTopArtists.adapter = artistsAdapter
        binding.tvAboutGenre.text = "About $genreName"
    }

    fun setupObservers(){
        viewModel.genresInfo.observe(requireActivity()){
            if(it.get("wiki").asJsonObject.get("content")!=null)
                binding.tvGenreDetails.text = it.get("wiki").asJsonObject.get("content").toString().replace("\"","")
            else
                binding.tvGenreDetails.text = "Falied to load information"
        }

        viewModel.album.observe(requireActivity()){
            if(it!=null)
                albumsAdapter.submitList(it.toList() as ArrayList<JsonObject>)
        }

        viewModel.artists.observe(requireActivity()){
            if(it!=null)
                artistsAdapter.submitList(it.toList() as ArrayList<JsonObject>)
        }

    }

    private fun setUpViewModel(){
        viewModel = ViewModelProvider(
            this,
            GenreViewModelFactory((requireActivity().application as MyApp).repository)
        )[GenresViewModel::class.java]
        viewModel.getGenreInfo(genreName)
        viewModel.getAlbum(genreName)
        viewModel.getArtists(genreName)
    }

    override fun onClick(album: JsonObject, position: Int,isArtist: Boolean) {
        if(isArtist){
           findNavController().navigate(GenreDetailsFragmentDirections.actionGenreDetailsFragmentToArtistDetailsFragment(album.get("name").toString().replace("\"", "")))
        }
        else{
            findNavController().navigate(GenreDetailsFragmentDirections.actionGenreDetailsFragmentToAlbumDetailsFragment(position,args.value.genreName))

        }
    }



}