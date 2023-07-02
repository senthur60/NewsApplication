package com.example.newsapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.Network.NewsNetwork
import com.example.newsapplication.data.NewsData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class NewsViewModel(private val newsApi:NewsNetwork):ViewModel() {



    fun fetchNews()  = flow {
        //viewModelScope.launch {
            while (true) {
                emit(newsApi.fetchNews())
                delay(500)
            }
       // }
    }
}