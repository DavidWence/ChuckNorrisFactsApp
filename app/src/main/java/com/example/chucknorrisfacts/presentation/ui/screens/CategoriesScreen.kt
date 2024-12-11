package com.example.chucknorrisfacts.presentation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chucknorrisfacts.domain.entity.Category
import com.example.chucknorrisfacts.presentation.util.UiState
import com.example.chucknorrisfacts.presentation.viewmodel.CategoriesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = koinViewModel(),
    onExitApp: () -> Unit,
    onCategorySelected: (category: Category) -> Unit){

    val uiState by viewModel.uiStateData.collectAsState()
    val categories by viewModel.categories.observeAsState(initial = emptyList())

    CategoriesContent(
        uiState = uiState,
        categories = categories,
        onCategorySelected = onCategorySelected)

    // override onBack to exit app
    BackHandler(enabled = true) { onExitApp() }
}

@Composable
fun CategoriesContent(
    uiState: UiState = UiState.Idle,
    categories: List<Category> = emptyList(),
    onCategorySelected: (category: Category) -> Unit = {}){
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
        Box(Modifier.padding(innerPadding)){
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)) {

                //content, show only if loading is done
                if(uiState is UiState.Finished){
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (categories.isNotEmpty())
                            LazyColumn(
                                flingBehavior = ScrollableDefaults.flingBehavior(),
                                state = rememberLazyListState(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(categories) { CategoryItem(
                                    category = it, onCategorySelected = onCategorySelected)
                                }
                            }
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

@Composable
fun CategoryItem(category: Category, onCategorySelected: (category: Category) -> Unit){
    Card {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable { onCategorySelected(category) }) {
            Text(
                text = category.label,
                style = MaterialTheme.typography.labelMedium)
        }
    }
}