package com.example.musicwiki.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicwiki.R
import com.example.musicwiki.databinding.RvItemTopBinding
import com.google.gson.JsonObject

class TopItemsAdapter(private val context: Context, private val onRemoveListener: OnClickListener, private val isArtist: Boolean=false) :
    ListAdapter<JsonObject, TopItemsAdapter.ViewHolder>(
        DiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemTopBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val position = holder.adapterPosition
        holder.binding.apply {
            val item = getItem(position)

            if(!isArtist){
                tvAlbumTitle.text = item.get("name").toString().replace("\"", "")
                tvAlbumArtist.text =
                    item.get("artist").asJsonObject.get("name").toString().replace("\"", "")
                Glide.with(context).load(
                    item.get("image").asJsonArray.get(2).asJsonObject.get("#text").toString()
                        .replace("\"", "")
                ).placeholder(R.drawable.ic_album).into(ivAlbum)
            }
            else {
                Glide.with(context).load(
                    item.get("image").asJsonArray.get(2).asJsonObject.get("#text").toString()
                        .replace("\"", "")
                ).placeholder(R.drawable.ic_person).into(ivAlbum)
                tvAlbumTitle.visibility = View.GONE
                tvArtistTitle.visibility = View.VISIBLE
                tvAlbumArtist.visibility = View.GONE
                tvArtistTitle.text = item.get("name").toString().replace("\"", "")
            }

            holder.binding.root.setOnClickListener {
                onRemoveListener.onClick(getItem(position), position, isArtist)
            }
        }
    }

    inner class ViewHolder(val binding: RvItemTopBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<JsonObject>() {
        override fun areItemsTheSame(oldItem: JsonObject, newItem: JsonObject) = oldItem == newItem
        override fun areContentsTheSame(oldItem: JsonObject, newItem: JsonObject) =
            oldItem == newItem
    }

    interface OnClickListener {
        fun onClick(mediaFile: JsonObject, position: Int,isArtist: Boolean)
    }

}