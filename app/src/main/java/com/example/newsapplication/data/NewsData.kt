package com.example.newsapplication.data


import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("articles")
    val articles: List<Article?>? = listOf(),
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("totalResults")
    val totalResults: Int? = 0
)