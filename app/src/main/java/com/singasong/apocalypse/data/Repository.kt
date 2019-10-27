package com.singasong.apocalypse.data

import android.content.res.Resources
import com.singasong.apocalypse.data.model.NumberKey

interface Repository {

    fun getLastScene(): List<NumberKey>

    fun storeLastScene(numberKeys: List<NumberKey>)

    fun storeChosenNumber(number: String?)

    fun getStoredChosenNumber(): String?
}