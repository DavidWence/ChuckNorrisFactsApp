package com.example.chucknorrisfacts.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.chucknorrisfacts.domain.entity.Category
import com.example.chucknorrisfacts.presentation.ui.screens.CategoriesScreen
import com.example.chucknorrisfacts.presentation.ui.screens.FactDetailsScreen
import com.example.chucknorrisfacts.presentation.ui.theme.ChuckNorrisFactsTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity: AppCompatActivity() {

    @Serializable
    private object Categories
    @Serializable
    private data class FactDetails(val category: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ChuckNorrisFactsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Categories){
                    composable<Categories> {
                        CategoriesScreen(
                            onExitApp = { finish() },
                            onCategorySelected = { category ->
                                navController.navigate(
                                    route = FactDetails(category = Json.encodeToString(category)))
                            })
                    }
                    composable<FactDetails> { backStackEntry ->
                        val factDetails: FactDetails = backStackEntry.toRoute()
                        FactDetailsScreen(
                            category = Json.decodeFromString<Category>(factDetails.category))
                    }
                }
            }
        }
    }

}