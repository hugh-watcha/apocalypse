package com.singasong.apocalypse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.singasong.apocalypse.model.NumberKey

const val KEY_NUMBERS = "numbers"

class MainViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val numberKeys: LiveData<List<NumberKey>> =
        savedStateHandle.getLiveData(KEY_NUMBERS)


}