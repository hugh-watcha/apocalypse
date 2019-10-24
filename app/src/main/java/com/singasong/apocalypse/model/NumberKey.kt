package com.singasong.apocalypse.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NumberKey(val number: String) : Parcelable {
    companion object {
        const val NUMBER_NOTICE = "0"
        fun createDefault(): NumberKey = NumberKey(NUMBER_NOTICE)
    }
}