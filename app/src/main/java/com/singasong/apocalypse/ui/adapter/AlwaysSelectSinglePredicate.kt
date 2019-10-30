package com.singasong.apocalypse.ui.adapter

import androidx.recyclerview.selection.SelectionTracker

class AlwaysSelectSinglePredicate<K> : SelectionTracker.SelectionPredicate<K>() {

    private var selectedKey: K? = null

    override fun canSelectMultiple(): Boolean {
        return false
    }

    override fun canSetStateForKey(key: K, nextState: Boolean): Boolean {
        if (nextState) {
            selectedKey = key
        } else if (selectedKey == key) {
            return false
        }
        return true
    }

    override fun canSetStateAtPosition(position: Int, nextState: Boolean): Boolean {
        return true
    }
}