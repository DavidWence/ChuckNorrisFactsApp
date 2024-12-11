package com.example.chucknorrisfacts.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chucknorrisfacts.presentation.ui.theme.BackgroundLoader

@Composable
@Preview
fun LoaderFullscreen(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLoader)) {
        CircularProgressIndicator()
    }
}