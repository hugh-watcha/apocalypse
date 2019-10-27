package com.singasong.apocalypse.ui.adapter

import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.StableIdKeyProvider

class MainTabKeyProvider(private val adapter: BaseSelectionRecyclerAdapter<*, *>) :
    ItemKeyProvider<String>(
        SCOPE_MAPPED
    ) {
    override fun getKey(position: Int): String? {
        return adapter.getKey(position)
    }

    override fun getPosition(key: String): Int {
        return adapter.getPosition(key)
    }
}