package com.singasong.apocalypse.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import com.singasong.apocalypse.ApocalypseApp
import com.singasong.apocalypse.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        SavedStateViewModelFactory(ApocalypseApp.instance, this)
    })


}