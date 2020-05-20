package com.sofascorezadatak.sofascorezadatak.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Repository(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("owner") val user: User,
    @field:SerializedName("watchers_count") val watchersCount: Int,
    @field:SerializedName("forks_count") val forksCount: Int,
    @field:SerializedName("open_issues_count") val openIssuesCount: Int,
    @field:SerializedName("language") val language: String,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("updated_at") val updatedAt: String,
    @field:SerializedName("html_url") val htmlUrl: String,
    @field:SerializedName("description") val description: String
) :
    Serializable