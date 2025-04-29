package com.example.newsapp.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.data.model.Article


@SuppressLint("RestrictedApi")
@Composable
fun ArticleItem(article: Article, navController: NavController, index: Int) {

    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                navController.currentBackStackEntry?.savedStateHandle?.set("type",
                    if(index == 0) "video" else "text")
                navController.navigate("detail")
            }
    ) {
        GlideImage(
            imageUrl = article.urlToImage ?: "",
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
        ) {
            Text(text = article.title, style = MaterialTheme.typography.h6,
                maxLines = 2)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = article.description ?: "", style = MaterialTheme.typography.body2,
                maxLines = 3)
        }
    }

}