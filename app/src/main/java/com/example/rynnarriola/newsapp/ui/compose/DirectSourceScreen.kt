package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rynnarriola.newsapp.base.ShowError
import com.example.rynnarriola.newsapp.base.ShowLoading
import com.example.rynnarriola.newsapp.data.model.Article
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.viewmodel.SourceViewModel

@Composable
fun DirectSourceScreen(newsSource : String?, viewModel: SourceViewModel = hiltViewModel(), onNewsClick: (url: String) -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (newsSource != null) {
        viewModel.fetchNews(newsSource)
    }

    when (uiState) {
        is UiState.Success -> {
            ArticleList((uiState as UiState.Success<List<Article>>).data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError((uiState as UiState.Error).message)
        }
    }
}