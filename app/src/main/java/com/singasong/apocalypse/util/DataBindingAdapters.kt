package com.singasong.apocalypse.util

import android.os.Build
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.singasong.apocalypse.R
import com.singasong.apocalypse.data.model.NumberKey
import com.singasong.apocalypse.network.Resource
import com.singasong.apocalypse.ui.adapter.BaseSelectionRecyclerAdapter
import java.text.DateFormat
import java.util.*

@BindingAdapter("app:list")
fun <T> bindList(recyclerView: RecyclerView, list: List<T>?) {
    @Suppress("UNCHECKED_CAST")
    (recyclerView.adapter as? ListAdapter<T, RecyclerView.ViewHolder>)?.submitList(list)
}

@BindingAdapter("app:selection")
fun setSelection(recyclerView: RecyclerView, key: String?) {
    if (key != null) {
        (recyclerView.adapter as? BaseSelectionRecyclerAdapter<*, *>)?.tracker?.select(key)
    }
}

@BindingAdapter("app:tabText")
fun setTabText(textView: TextView, text: String?) {
    if (text == NumberKey.NUMBER_NOTICE) {
        textView.setText(R.string.notice)
    } else {
        textView.text = text
    }
}

@BindingAdapter("android:text")
fun setDate(textView: TextView, date: Date?) {
    if (date != null) {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.resources.configuration.locales[0]
        } else {
            textView.resources.configuration.locale
        }
        textView.setText(DateFormat.getDateInstance(DateFormat.DEFAULT, locale).format(date))
    }
}

@BindingAdapter("app:resourceList")
fun <T> bindResourceList(recyclerView: RecyclerView, resourceList: Resource<List<T>>?) {
    (recyclerView.adapter as? ListAdapter<T, RecyclerView.ViewHolder>)?.submitList(resourceList?.data)
}