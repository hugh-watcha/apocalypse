package com.singasong.apocalypse.network

import androidx.lifecycle.LiveData
import com.singasong.apocalypse.data.model.Post
import com.singasong.apocalypse.data.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    fun getPosts(@Query("target_number") targetNumber: String): LiveData<ApiResponse<List<Post>>>

    @GET("users/me")
    fun getMe(): LiveData<ApiResponse<User>>
}