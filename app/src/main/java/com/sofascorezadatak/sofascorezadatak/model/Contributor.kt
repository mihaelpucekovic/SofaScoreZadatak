package com.sofascorezadatak.sofascorezadatak.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Contributor(
    @field:SerializedName("login") val login: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    @field:SerializedName("html_url") val htmlUrl: String,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("contributions") val contributions: Int
) :
    Serializable