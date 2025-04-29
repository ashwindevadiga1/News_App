package com.example.newsapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.newsapp.data.model.Article

@Composable
fun NavGraph(navController: NavHostController, startDestination: String = "splash") {
    val navGraph = navController.createGraph(startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("main") { NewsScreen(navController) }
        composable("detail") {
            val article = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Article>("article")
            val type = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<String>("type")
            ArticleDetailScreen(navController, article, type = type.orEmpty())
        }
        composable("tagListing") {
            val article = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<String>("tag")
            TagListingScreen(navController, article.orEmpty())
        }
    }
    NavHost(navController = navController, graph = navGraph)

}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavGraph(navController)
}