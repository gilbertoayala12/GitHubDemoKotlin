package com.ivanebernal.githubdemokotlin

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by welcome1 on 10/13/17.
 */
interface GithubClient {
    @GET("/search/users")
    fun reposForUser(@Query("q") user: String): Call<GitHubSearchResult>
}