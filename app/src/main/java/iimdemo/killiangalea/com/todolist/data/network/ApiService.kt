package iimdemo.killiangalea.com.todolist.data.network

import iimdemo.killiangalea.com.todolist.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("login")
    fun login(
        @Query("email") email: String,
        @Query("pwd") pwd: String
    ): Call<User>
}