package com.example.musicwiki.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.databinding.RvItemBinding
import com.google.gson.JsonObject

class GenresAdapter(var mContext: Context, private val onRemoveListener: OnClickListener) :
    ListAdapter<JsonObject, GenresAdapter.ViewHolder>(
        DiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val position = holder.adapterPosition
        holder.binding.apply {

            holder.binding.root.text = getItem(position).get("name").toString().replace("\"","")
            holder.binding.root.setOnClickListener {
                onRemoveListener.onClick(getItem(position), position)
            }
        }
    }

    inner class ViewHolder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<JsonObject>() {
        override fun areItemsTheSame(oldItem: JsonObject, newItem: JsonObject) = oldItem == newItem
        override fun areContentsTheSame(oldItem: JsonObject, newItem: JsonObject) = oldItem == newItem
    }

    interface OnClickListener {
        fun onClick(mediaFile: JsonObject, position: Int)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

}