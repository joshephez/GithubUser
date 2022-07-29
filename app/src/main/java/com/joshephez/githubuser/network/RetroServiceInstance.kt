package com.joshephez.githubuser.network

import com.joshephez.githubuser.config.Constant
import com.joshephez.githubuser.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroServiceInstance {


    @GET("users")
    suspend fun getUsers():Response<UsersResponse>

    @GET("search/users")
    suspend fun searchUsers(@Query("q")query: String):Response<UsersSearchResponse>

    @GET("users/{username}")
    suspend fun getUsersByName(@Path("username")username:String):Response<UserDetailResponse>

    @GET("users/{username}/repos")
    suspend fun  getReposByName(@Path("username")username: String):Response<RepoResponse>

}