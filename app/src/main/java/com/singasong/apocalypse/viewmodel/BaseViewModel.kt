package com.singasong.apocalypse.viewmodel

import androidx.lifecycle.ViewModel
import com.singasong.apocalypse.data.Repository

abstract class BaseViewModel(protected val repository: Repository) : ViewModel()