package com.example.chucknorrisfacts.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chucknorrisfacts.domain.entity.Category
import com.example.chucknorrisfacts.domain.entity.Fact
import com.example.chucknorrisfacts.presentation.util.UiState
import com.example.chucknorrisfacts.presentation.viewmodel.FactDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FactDetailsScreen(
    category: Category,
    viewModel: FactDetailsViewModel = koinViewModel()){

    val uiState by viewModel.uiStateData.collectAsState()
    val fact by viewModel.fact.observeAsState()

    var initialLoading by rememberSaveable { mutableStateOf(false) }
    if(!initialLoading){
        initialLoading = true
        viewModel.load(category)
    }

    MessagesContent(
        uiState = uiState,
        category = category,
        fact = fact)
}

@Composable
fun MessagesContent(
    uiState: UiState = UiState.Idle,
    category: Category,
    fact: Fact? = null){
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
        Box(Modifier.padding(innerPadding)){
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)) {

                //content, show only if loading is done
                if(uiState is UiState.Finished && fact != null){
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = fact.description,
                            style = MaterialTheme.typography.labelMedium)
                        Text(
                            text = category.label,
                            style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            if(!uiState.uiEnabled)
                LoaderFullscreen()

            LaunchedEffect(uiState, snackbarHostState){
                if(uiState is UiState.Error)
                    snackbarHostState.showSnackbar(
                        message = uiState.message, duration = SnackbarDuration.Short)
            }
        }
    }
}