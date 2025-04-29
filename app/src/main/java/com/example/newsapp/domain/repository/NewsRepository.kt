package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.NewsResponse

interface NewsRepository {
    suspend fun getTopHeadlines(country: String, category: String, selectedLanguage: String, apiKey: String): NewsResponse
}