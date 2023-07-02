package com.example.newsapplication.Network

import android.util.Log
import com.example.newsapplication.data.NewsData
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request

class NewsNetwork{
    val okHttpClient = OkHttpClient()

    suspend fun fetchNews() : NewsData{
        return withContext(Dispatchers.IO){
            val builder = Request.Builder()
            builder.url("https://newsapi.org/v2/everything?q=tesla&from=2023-06-02&sortBy=publishedAt&apiKey=9dcdcc92142e49358dc167dad9e655b8")
            val response = okHttpClient.newCall(builder.build()).execute()
            return@withContext Gson().fromJson(response.body!!.string(),NewsData::class.java)
        }
    }
}