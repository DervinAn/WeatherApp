package com.example.ktor.util.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier.padding(16.dp))
}

