package com.ivanebernal.githubdemokotlin.models

import com.google.gson.annotations.SerializedName
import com.ivanebernal.githubdemokotlin.models.GitHubUser

/**
 * Created by welcome1 on 10/13/17.
 */
data class GitHubSearchResult(
        @SerializedName("total_count") val totalCount: Int,
        @SerializedName("incomplete_results") val incompleteResults: Boolean,
        @SerializedName("items") val items: List<GitHubUser>

)
