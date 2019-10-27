package com.singasong.apocalypse.ui.adapter

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseSelectionRecyclerAdapter<T, VH : BaseSelectionRecyclerAdapter.ViewHolder<T, *>> :
    ListAdapter<T, VH> {

    var tracker: SelectionTracker<String>? = null

    constructor() : this(object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    })

    constructor(diffCallback: DiffUtil.ItemCallback<T>) : super(diffCallback)
    constructor(config: AsyncDifferConfig<T>) : super(config)

    abstract fun getKey(position: Int): String?

    abstract fun getPosition(key: String): Int

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(getItem(position), tracker?.isSelected(getKey(position)) ?: false)
    }

    abstract class ViewHolder<T, VB : ViewDataBinding>(private val viewBinding: VB) :
        RecyclerView.ViewHolder(viewBinding.root) {

        abstract fun bind(viewBinding: VB, data: T)

        fun bindData(data: T, activated: Boolean) {
            bind(viewBinding, data)
            viewBinding.executePendingBindings()

            viewBinding.root.isActivated = activated
        }
    }
}