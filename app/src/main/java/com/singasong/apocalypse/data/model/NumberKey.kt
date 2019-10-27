package com.singasong.apocalypse.data.model

import android.content.res.Resources
import android.os.Parcelable
import com.singasong.apocalypse.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NumberKey(val number: String, val name: String? = null) :
    Parcelable {
    companion object {
        const val NUMBER_NOTICE = "0"
        private const val FORMAT_NUMBER = "number:"
        private const val FORMAT_NAME = ", name:"
        private const val FORMAT = "$FORMAT_NUMBER%s$FORMAT_NAME%s"

        fun createDefault(): NumberKey = NumberKey(NUMBER_NOTICE)

        fun createFromString(string: String): NumberKey {
            val number: String =
                string.substring(FORMAT_NUMBER.length, string.lastIndexOf(FORMAT_NAME))
            val name: String =
                string.substring(string.lastIndexOf(FORMAT_NAME) + FORMAT_NAME.length)
            return NumberKey(number, name)
        }
    }

    override fun toString(): String {
        return FORMAT.format(number, name)
    }
}