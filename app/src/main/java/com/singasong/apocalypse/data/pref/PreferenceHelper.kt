package com.singasong.apocalypse.data.pref

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.singasong.apocalypse.data.model.NumberKey

class PreferenceHelper(context: Context) {
    companion object {
        private const val FILE_NAME = "apocalypse_shared_prefs"

        private const val KEY_NUMBER_KEYS = "number_keys"
        private const val KEY_CHOSEN_NUMBER = "chosen_number"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    fun getLastScene(): List<NumberKey> {
        val keys = sharedPreferences.getStringSet(KEY_NUMBER_KEYS, emptySet<String>())
        val numberKeys = keys?.map {
            NumberKey.createFromString(it)
        }
        return if (numberKeys.isNullOrEmpty()) {
            listOf(NumberKey.createDefault())
        } else {
            numberKeys
        }
    }

    fun storeLastScene(numberKeys: List<NumberKey>) {
        sharedPreferences.edit().putStringSet(KEY_NUMBER_KEYS, numberKeys.map {
            it.toString()
        }.toSet()).apply()
    }

    fun getStoredChosenNumber(): String? {
        return sharedPreferences.getString(KEY_CHOSEN_NUMBER, NumberKey.NUMBER_NOTICE)
    }

    fun storeChosenNumber(number: String?) {
        sharedPreferences.edit().putString(KEY_CHOSEN_NUMBER, number).apply()
    }


}