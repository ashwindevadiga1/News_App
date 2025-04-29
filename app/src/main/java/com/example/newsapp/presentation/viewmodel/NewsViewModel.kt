package com.example.newsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.usecase.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines(country: String = "us", category: String = "business", selectedLanguage: String = "en") {
        viewModelScope.launch {
            val response = getTopHeadlinesUseCase(country, category,selectedLanguage, "8257ac2f5fc94ceba2945f7899b2bbba")
            _articles.value = response.articles
        }

    }

    fun refreshArticles(selectedCountry: String, selectedCategory: String, selectedLanguage: String = "en") {
        fetchTopHeadlines(selectedCountry, selectedCategory, selectedLanguage)
    }
}