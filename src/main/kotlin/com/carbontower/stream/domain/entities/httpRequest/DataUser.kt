package com.carbontower.stream.domain.entities.httpRequest

import com.google.gson.annotations.SerializedName

data class DataUser(
    @SerializedName("broadcaster_type")
    val broadcasterType: String?,
    val description: String?,
    @SerializedName("display_name")
    val displayName: String,
    val email: String?,
    val id: String,
    val login: String,
    @SerializedName("offline_image_url")
    val offlineImageUrl: String?,
    @SerializedName("profile_image_url")
    val profileImageUrl: String,
    val type: String?,
    @SerializedName("view_count")
    val viewCount: Int
)