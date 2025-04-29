package com.example.newsapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000) // 3 seconds delay
        navController.navigate("main") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF2196F3), Color(0xFF03A9F4))
                )
            )
    ) {
        Text(text = "Welcome to NewsApp", style = MaterialTheme.typography.h4)
    }
}