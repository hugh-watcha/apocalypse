package com.singasong.apocalypse.ui.adapter

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class MainTabDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<String>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<String>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        return if (view != null)
            (recyclerView.getChildViewHolder(view) as? MainBottomTabLayoutAdapter.ViewHolder)?.getItemDetails()
        else null
    }
}