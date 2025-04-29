package com.example.newsapp.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.presentation.viewmodel.NewsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TagListingScreen(navController: NavController, tag: String,newsViewModel: NewsViewModel = hiltViewModel()) {
    val articles by newsViewModel.articles.collectAsState()
    LaunchedEffect(Unit) {
        newsViewModel.refreshArticles("us", tag)
    }

    Box {
        LazyColumn {
            stickyHeader {
                Text(
                    text = tag,
                    style = MaterialTheme.typography.h4.copy(color = Color.White),
                    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primary).padding(16.dp)
                )
            }
            itemsIndexed(articles) { index, article ->
                ArticleItem(article, navController, index)
                Divider(color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
