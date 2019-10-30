package com.singasong.apocalypse.data

import androidx.lifecycle.LiveData
import com.singasong.apocalypse.data.model.NumberKey
import com.singasong.apocalypse.data.model.Post
import com.singasong.apocalypse.network.Resource

interface Repository {

    fun getLastScene(): List<NumberKey>

    fun storeLastScene(numberKeys: List<NumberKey>)

    fun storeChosenNumber(number: String?)

    fun getStoredChosenNumber(): String?

    fun loadPosts(key: String): LiveData<Resource<List<Post>>>
}