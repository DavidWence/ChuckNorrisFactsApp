package com.example.chucknorrisfacts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisfacts.domain.entity.Category
import com.example.chucknorrisfacts.domain.entity.Outcome
import com.example.chucknorrisfacts.domain.usecases.GetCategories
import com.example.chucknorrisfacts.presentation.util.UiState
import kotlinx.coroutines.launch

class CategoriesViewModel(private val getCategories: GetCategories): SimpleProcessViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    init { load() }

    private fun load(){
        viewModelScope.launch {
            uiState.value = UiState.Loading
            when (val result = getCategories()) {
                is Outcome.Error -> uiState.value = UiState.Error(result.description.asText())
                is Outcome.Success -> {
                    uiState.value = UiState.Finished
                    _categories.value = result.data
                }
                else -> uiState.value = UiState.Idle
            }
        }
    }

}