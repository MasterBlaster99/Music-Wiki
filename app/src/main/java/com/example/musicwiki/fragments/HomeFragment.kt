package com.example.musicwiki.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.musicwiki.MyApp
import com.example.musicwiki.R
import com.example.musicwiki.adapters.GenresAdapter
import com.example.musicwiki.databinding.FragmentHomeBinding
import com.example.musicwiki.viewmodel.GenreViewModelFactory
import com.example.musicwiki.viewmodel.GenresViewModel
import com.google.gson.JsonObject

class HomeFragment : Fragment(R.layout.fragment_home),GenresAdapter.OnClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: GenresViewModel
    private val list = ArrayList<JsonObject>()
    private var isExpanded = false
    private lateinit var adapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        adapter = GenresAdapter(requireContext(),this)
        binding.rvGenres.adapter = adapter
        setUpViewModel()
        setUpObservers()
        setUpListeners()
        return binding.root
    }

    private fun setUpViewModel(){
        viewModel = ViewModelProvider(
            this,
            GenreViewModelFactory((requireActivity().application as MyApp).repository)
        )[GenresViewModel::class.java]

        viewModel.getAllGenres("https://ws.audioscrobbler.com/2.0/?method=tag.getTopTags&api_key=ae06783a7d927f4b558c5f7d4041d270&format=json")
    }

    private fun setUpObservers(){
        viewModel.genres.observe(requireActivity()){
            if(!isExpanded){
                list.clear()
                for (i in 0..9){
                    list.add(it.asJsonArray.get(i).asJsonObject)
                }
                adapter.submitList(list)
            }
            else {
                adapter.submitList(it.toList() as ArrayList<JsonObject>)
            }
        }
    }
    private fun setUpListeners(){
        binding.ivShuffle.setOnClickListener{
            if(isExpanded){
                isExpanded = false
                binding.ivShuffle.setImageResource(R.drawable.ic_arrow_down)
            }
            else {
                isExpanded = true
                binding.ivShuffle.setImageResource(R.drawable.ic_arrow_up)
            }

            setUpViewModel()
        }
    }

    override fun onClick(genre: JsonObject, position: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGenreDetailsFragment(genre.get("name").toString().replace("\"","")))
    }
}