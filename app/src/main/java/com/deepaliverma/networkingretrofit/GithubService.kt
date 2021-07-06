package com.deepaliverma.networkingretrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    //here abc will be copied to id
    @GET("users/{id}")
    suspend fun getUsersbyId(@Path("id") id: String): Response<User>

    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): Response<List<User>>
}