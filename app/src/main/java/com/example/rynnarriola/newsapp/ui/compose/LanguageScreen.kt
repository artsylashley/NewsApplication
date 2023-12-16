package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rynnarriola.newsapp.base.Screen
import com.example.rynnarriola.newsapp.base.ShowError
import com.example.rynnarriola.newsapp.base.ShowLoading
import com.example.rynnarriola.newsapp.data.model.Language
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.viewmodel.LanguageViewModel

@Composable
fun LanguageScreen(viewModel: LanguageViewModel = hiltViewModel(), navController: NavController) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is UiState.Success -> {
            LanguageAdapter((uiState as UiState.Success<List<Language>>).data, navController)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError((uiState as UiState.Error).message)
        }
    }
}

@Composable
fun LanguageAdapter(languages: List<Language>, navController: NavController) {
    LazyColumn {
        items(languages) { language ->
            LanguageItem(language = language, navController)
        }
    }
}

@Composable
fun LanguageItem(language: Language, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
               navController.navigate(Screen.LanguageNewsScreen.withArgs(language.code))
            }
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)) // Set the background color and rounded corners
    ) {
        Text(
            text = language.language,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White, // Set the text color to white
            modifier = Modifier
                .fillMaxWidth() // Fill the maximum width of the text
                .padding(16.dp), // Add padding to center the text within the box
            textAlign = TextAlign.Center // Center the text within the box
        )
    }
}