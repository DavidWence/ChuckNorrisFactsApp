package com.example.chucknorrisfacts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisfacts.domain.entity.Category
import com.example.chucknorrisfacts.domain.entity.Fact
import com.example.chucknorrisfacts.domain.entity.Outcome
import com.example.chucknorrisfacts.domain.usecases.GetRandomFact
import com.example.chucknorrisfacts.presentation.util.UiState
import kotlinx.coroutines.launch

class FactDetailsViewModel(private val getRandomFact: GetRandomFact): SimpleProcessViewModel() {

    private val _fact = MutableLiveData<Fact>()
    val fact: LiveData<Fact> get() = _fact

    fun load(category: Category){
        viewModelScope.launch {
            uiState.value = UiState.Loading
            when (val result = getRandomFact(category)) {
                is Outcome.Error -> uiState.value = UiState.Error(result.description.asText())
                is Outcome.Success -> {
                    uiState.value = UiState.Finished
                    _fact.value = result.data
                }
                else -> uiState.value = UiState.Idle
            }
        }
    }

}