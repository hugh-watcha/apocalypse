package com.singasong.apocalypse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.singasong.apocalypse.data.Repository
import com.singasong.apocalypse.data.model.NumberKey
import com.singasong.apocalypse.data.model.Post
import com.singasong.apocalypse.network.Resource

const val KEY_NUMBER_KEY = "key_number_key"

class PostsViewModel(repository: Repository, savedStateHandle: SavedStateHandle) :
    BaseViewModel(repository) {

    private val numberKey: NumberKey =
        savedStateHandle[KEY_NUMBER_KEY] ?: throw IllegalArgumentException("number key is required")
    val posts: LiveData<Resource<List<Post>>> = repository.loadPosts(numberKey.number)
}