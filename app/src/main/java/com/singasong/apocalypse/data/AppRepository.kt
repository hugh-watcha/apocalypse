package com.singasong.apocalypse.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.singasong.apocalypse.data.model.NumberKey
import com.singasong.apocalypse.data.model.Post
import com.singasong.apocalypse.data.pref.PreferenceHelper
import com.singasong.apocalypse.network.*
import com.singasong.apocalypse.util.ALog

class AppRepository(
    private val apiService: ApiService,
    private val preferenceHelper: PreferenceHelper,
    private val appExecutors: AppExecutors
) : Repository {
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

    override fun loadPosts(key: String): LiveData<Resource<List<Post>>> {
        return object : NetworkBoundResource<List<Post>, List<Post>>(appExecutors) {
            override fun saveCallResult(item: List<Post>) {

            }

            override fun shouldFetch(data: List<Post>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Post>> {
                return MutableLiveData<List<Post>>()
            }

            override fun createCall(): LiveData<ApiResponse<List<Post>>> {
                return apiService.getPosts(key)
            }

            override fun onFetchFailed() {
                super.onFetchFailed()
                ALog.e("")
            }
        }.asLiveData()
    }
}