package com.sofascorezadatak.sofascorezadatak.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Pull(
    @field:SerializedName("url") val url: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("number") val number: Int,
    @field:SerializedName("state") val state: String,
    @field:SerializedName("user") val user: User,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("body") val body: String,
    @field:SerializedName("created_at") val created_at: String,
    @field:SerializedName("updated_at") val updated_at: String
) :
    Serializable