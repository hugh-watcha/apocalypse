package com.singasong.apocalypse.data

import android.content.res.Resources
import com.singasong.apocalypse.data.model.NumberKey
import com.singasong.apocalypse.data.pref.PreferenceHelper

class AppRepository(private val preferenceHelper: PreferenceHelper) : Repository {
    override fun getLastScene(): List<NumberKey> {
        return preferenceHelper.getLastScene()
    }

    override fun storeLastScene(numberKeys: List<NumberKey>) {
        preferenceHelper.storeLastScene(numberKeys)
    }

    override fun storeChosenNumber(number: String?) {
        preferenceHelper.storeChosenNumber(number)
    }

    override fun getStoredChosenNumber(): String? {
        return preferenceHelper.getStoredChosenNumber()
    }
}