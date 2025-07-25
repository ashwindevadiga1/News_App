package com.example.newsapp.data.api

import com.example.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
      //  @Query("language") selectedLanguage: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}