package com.ivanebernal.githubdemokotlin

import com.google.gson.annotations.SerializedName

/**
 * Created by welcome1 on 10/13/17.
 */
data class GitHubUser(
        @SerializedName("login") val login: String,
        @SerializedName("id") val id: Long,
        @SerializedName("avatar_url") val avatarURL: String,
        @SerializedName("gravatar_id") val gravatarID: String,
        @SerializedName("url") val url: String,
        @SerializedName("html_url") val htmlURL: String,
        @SerializedName("followers_url") val followersURL: String,
        @SerializedName("following_url") val followingURL: String,
        @SerializedName("gists_url") val gistsURL: String,
        @SerializedName("starred_url") val starredURL: String,
        @SerializedName("subscriptions_url") val subscriptionsURL: String,
        @SerializedName("organizations_url") val organizationsURL: String,
        @SerializedName("repos_url") val reposURL: String,
        @SerializedName("events_url") val eventsURL: String,
        @SerializedName("received_events_url") val receivedEventsURL: String,
        @SerializedName("type") val type: String,
        @SerializedName("site_admin") val siteAdmin: Boolean,
        @SerializedName("score") val score: Float
) {

}