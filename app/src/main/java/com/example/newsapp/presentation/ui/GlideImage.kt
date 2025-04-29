package com.example.newsapp.presentation.ui

import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@Composable
fun GlideImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                Glide.with(context)
                    .load(imageUrl)
                    .apply(RequestOptions().centerCrop())
                    .into(this)
            }
        },
        modifier = modifier
    )
}