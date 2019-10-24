package com.singasong.apocalypse.network

import com.singasong.apocalypse.model.Post
import com.singasong.apocalypse.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {
    @GET("posts?target_number={target_number}")
    fun getPosts(@Path("target_number") targetNumber: String): Call<List<Post>>

    @GET("users/me")
    fun getMe(): Call<User>
}