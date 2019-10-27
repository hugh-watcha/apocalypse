package com.singasong.apocalypse.network

import com.singasong.apocalypse.data.model.Post
import com.singasong.apocalypse.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts?target_number={target_number}")
    fun getPosts(@Path("target_number") targetNumber: String): Call<List<Post>>

    @GET("users/me")
    fun getMe(): Call<User>
}