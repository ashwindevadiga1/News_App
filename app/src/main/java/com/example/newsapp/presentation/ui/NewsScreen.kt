package com.example.newsapp.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.presentation.viewmodel.NewsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsScreen(navController: NavController, newsViewModel: NewsViewModel = hiltViewModel()) {
    val articles by newsViewModel.articles.collectAsState()
    val isRefreshing by newsViewModel.isRefreshing.collectAsState()

    var selectedCategory by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf("") }
    var selectedLanguage by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("News App") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        Box (modifier = Modifier.padding(paddingValues)){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Filters
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 20.dp)
                    .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.Center) {
                    FilterDropdownMenu(
                        selectedOption = if(selectedCategory.isEmpty()) "Category" else selectedCategory,
                        options = listOf("business", "general", "entertainment"),
                        onOptionSelected = { selectedCategory = it
                            newsViewModel.refreshArticles(
                                selectedCountry,
                                selectedCategory,
                                selectedLanguage
                            )}
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    FilterDropdownMenu(
                        selectedOption = if(selectedCountry.isEmpty()) "Country" else selectedCountry,
                        options = listOf("us", "gb", "in"),
                        onOptionSelected = { selectedCountry = it
                            newsViewModel.refreshArticles(
                                selectedCountry,
                                selectedCategory,
                                selectedLanguage
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    FilterDropdownMenu(
                        selectedOption = if(selectedLanguage.isEmpty()) "Language" else selectedLanguage,
                        options = listOf("en", "fr", "he"),
                        onOptionSelected = { selectedLanguage = it
                            newsViewModel.refreshArticles(selectedCountry, selectedCategory, selectedLanguage)
                        }
                    )
                }

                // Pull to refresh and article list
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing),
                    onRefresh = {
                        selectedCountry = "us"
                        selectedCategory = "business"
                        newsViewModel.refreshArticles(
                        selectedCountry,
                        selectedCategory,
                        selectedLanguage
                    ) }
                ) {
                    LazyColumn {
                        itemsIndexed(articles) { index, article ->
                            ArticleItem(article, navController, index)
                            Divider(color = Color.Gray)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterDropdownMenu(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedOption)
            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between text and icon
            Icon(
                imageVector = Icons.Default.ArrowDropDown, // Use a dropdown arrow icon
                contentDescription = "Dropdown Icon"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(option)
                    expanded = false
                }) {
                    Text(option)
                }
            }
        }
    }
}