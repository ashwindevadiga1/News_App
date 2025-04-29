package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(country: String, category: String, selectedLanguage: String, apiKey: String): NewsResponse {
        return newsRepository.getTopHeadlines(country, category, selectedLanguage, apiKey)
    }
}