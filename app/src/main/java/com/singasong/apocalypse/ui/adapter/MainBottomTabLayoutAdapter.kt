package com.singasong.apocalypse.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.singasong.apocalypse.data.model.NumberKey
import com.singasong.apocalypse.databinding.ViewMainBottomTabBinding

class MainBottomTabLayoutAdapter :
    BaseSelectionRecyclerAdapter<NumberKey, MainBottomTabLayoutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewMainBottomTabBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getKey(position: Int): String? {
        return getItem(position)?.number
    }

    override fun getPosition(key: String): Int {
        for (index in 0 until itemCount) {
            if (getItem(index).number == key) return index
        }
        return RecyclerView.NO_POSITION
    }

    class ViewHolder(dataBinding: ViewMainBottomTabBinding) :
        BaseSelectionRecyclerAdapter.ViewHolder<NumberKey, ViewMainBottomTabBinding>(dataBinding) {
        private var number: String? = null

        override fun bind(viewBinding: ViewMainBottomTabBinding, data: NumberKey) {
            viewBinding.numberKey = data
            number = data.number
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<String> {
            return object : ItemDetailsLookup.ItemDetails<String>() {
                override fun getSelectionKey(): String? {
                    return number
                }

                override fun getPosition(): Int {
                    return adapterPosition
                }
            }
        }
    }
}