package com.example.imdb.data.entity.inovacao

import com.google.gson.annotations.SerializedName

data class Test(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    val items: List<Item?>?,
    @SerializedName("total_count")
    val totalCount: Int?
)