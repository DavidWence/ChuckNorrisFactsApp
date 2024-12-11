package com.example.chucknorrisfacts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisfacts.presentation.util.UiState
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class SimpleProcessViewModel: ViewModel() {
    protected val uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiStateData: StateFlow<UiState> get() = uiState

    override fun onCleared() {
        viewModelScope.coroutineContext.cancelChildren()
    }
}