package com.ivanebernal.githubdemokotlin

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by welcome1 on 10/13/17.
 */
interface GithubClient {
    @GET("/users/{user}/repos")
    fun reposForUser(@Path("user") user: String): Observable<GitHubSearchResult>
}