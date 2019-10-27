package com.singasong.apocalypse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.singasong.apocalypse.data.Repository
import com.singasong.apocalypse.data.model.NumberKey

const val KEY_NUMBERS = "numbers"
const val KEY_CHOSEN_NUMBER = "chosen_number"

class MainViewModel(repository: Repository, savedStateHandle: SavedStateHandle) :
    BaseViewModel(repository) {
    val numberKeys: LiveData<List<NumberKey>> = MutableLiveData<List<NumberKey>>(
        savedStateHandle.get(KEY_NUMBERS) ?: repository.getLastScene()
    )

    var chosenNumber: String? =
        savedStateHandle[KEY_CHOSEN_NUMBER] ?: repository.getStoredChosenNumber()
        set(value) {
            field = value
            repository.storeChosenNumber(value)
        }
}