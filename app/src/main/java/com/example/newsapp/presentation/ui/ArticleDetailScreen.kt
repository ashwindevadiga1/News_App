package com.example.newsapp.presentation.ui

import android.R
import android.net.Uri
import android.webkit.WebView
import android.widget.VideoView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.newsapp.data.model.Article


@Composable
fun ArticleDetailScreen(navController: NavController, article: Article?, type: String) {
   // val article = navController.previousBackStackEntry?.arguments?.getParcelable<Article>("article")

    // article?.let {
    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        GlideImage(
            imageUrl = article?.urlToImage ?: "",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article?.title.orEmpty(), style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            GlideImage(
                imageUrl = article?.urlToImage ?: "",
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            )
            Text(text = article?.author.orEmpty(), style = MaterialTheme.typography.body1)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article?.description ?: "", style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.height(8.dp))


        when (type) {
       // http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4
            "text" -> {
                AndroidView(factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true // Enable JavaScript
                        settings.setSupportZoom(true) // Optional: Enable zoom controls
                        settings.domStorageEnabled = true // Optional: Enable DOM storage
                        settings.blockNetworkLoads = false // Disable ad blocker (if applicable)

                        loadUrl(article?.url.orEmpty())
                    }
                }, modifier = Modifier.fillMaxSize())
            }
            "video" -> {

                ExoPlayerView()
                /*AndroidView(factory = { context ->

                    android.widget.VideoView(context).apply {
                        //setVideoPath("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
                        val vidAddress =
                            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"
                        val videoUri = Uri.parse(vidAddress)
                        setVideoURI(videoUri)
                        setOnPreparedListener { it.start() } // Start playback when ready
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f))*/
            }
        }

        Row {
            OutlinedButton(
                onClick = {
                    // Handle button click
                    navController.currentBackStackEntry?.savedStateHandle?.set("tag", "Business")

                    navController.navigate("tagListing")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Business")
            }
            OutlinedButton(
                onClick = {
                    // Handle button click
                    navController.currentBackStackEntry?.savedStateHandle?.set("tag", "General")
                    navController.navigate("tagListing")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "General")
            }
            OutlinedButton(
                onClick = {
                    // Handle button click
                    navController.currentBackStackEntry?.savedStateHandle?.set("tag", "Entertainment")

                    navController.navigate("tagListing")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Entertainment")
            }
        }

    }
}

const val EXAMPLE_VIDEO_URI = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

/**
 * Composable function that displays an ExoPlayer to play a video using Jetpack Compose.
 *
 * @OptIn annotation to UnstableApi is used to indicate that the API is still experimental and may
 * undergo changes in the future.
 *
 * @see EXAMPLE_VIDEO_URI Replace with the actual URI of the video to be played.
 */
@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(UnstableApi::class)
@Composable
fun ExoPlayerView() {
    // Get the current context
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build()

    // Create a MediaSource
    val mediaSource = remember(EXAMPLE_VIDEO_URI) {
        MediaItem.fromUri(EXAMPLE_VIDEO_URI)
    }

    // Set MediaSource to ExoPlayer
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    // Manage lifecycle events
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Use AndroidView to embed an Android View (PlayerView) into Compose
    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                player?.play()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Set your desired height
    )
}
