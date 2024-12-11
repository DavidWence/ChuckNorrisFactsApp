package com.example.chucknorrisfacts.presentation.util

sealed class UiState(val uiEnabled: Boolean) {
    data object Idle: UiState(true)
    data object Loading: UiState(false)
    data class Error(val message: String): UiState(true)
    data object Finished: UiState(true)
}