package com.singasong.apocalypse.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.singasong.apocalypse.data.model.Post
import com.singasong.apocalypse.databinding.ViewPostBinding

class PostsAdapter :
    ListAdapter<Post, PostsAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.text == newItem.text
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewPostBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ViewHolder(private val dataBinding: ViewPostBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bindData(data: Post) {
            dataBinding.post = data
            dataBinding.executePendingBindings()
        }
    }
}