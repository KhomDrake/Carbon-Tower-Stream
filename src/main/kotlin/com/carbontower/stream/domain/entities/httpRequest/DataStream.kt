package com.carbontower.stream.domain.entities.httpRequest

import com.google.gson.annotations.SerializedName

data class DataStream(
    @SerializedName("community_ids")
    val communityIds: List<String>?,
    @SerializedName("game_id")
    val gameId: String?,
    val id: Int,
    val language: String,
    @SerializedName("started_at")
    val startedAt: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
    val title: String?,
    val type: String?,
    @SerializedName("user_id")
    val userId: String?,
    @SerializedName("user_name")
    val userName: String?,
    @SerializedName("viewer_count")
    val viewerCount: Int
)