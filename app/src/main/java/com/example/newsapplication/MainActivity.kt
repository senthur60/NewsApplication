package com.example.newsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.example.newsapplication.Network.NewsNetwork
import com.example.newsapplication.data.NewsData
import com.example.newsapplication.ui.theme.NewsApplicationTheme
import com.example.newsapplication.viewModel.NewsViewModel

class MainActivity : ComponentActivity() {


    val newsViewModel : NewsViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NewsViewModel(NewsNetwork()) as T
            }
        })[NewsViewModel::class.java]
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val fetchNews = newsViewModel.fetchNews().collectAsState(NewsData())
            LazyColumn{
                items(fetchNews.value.articles!!.size)
                { item ->
                    ListItem(headlineText = {
                        Text(text = fetchNews.value.articles!![item]!!.title!!)
                    }, leadingContent = {
                        AsyncImage(modifier = Modifier.size(100.dp),model = fetchNews.value.articles!![item]!!.urlToImage, contentDescription = " ")

                    })
                }
            }
            
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsApplicationTheme {
        Greeting("Android")
    }
}