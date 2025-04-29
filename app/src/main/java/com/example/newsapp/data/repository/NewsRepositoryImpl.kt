package com.example.newsapp.data.repository

import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(country: String, category: String, selectedLanguage: String, apiKey: String): NewsResponse {
        return api.getTopHeadlines(country, category, apiKey)
    }
}

