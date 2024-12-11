package com.example.chucknorrisfacts.di

import com.example.chucknorrisfacts.data.api.FactsService
import com.example.chucknorrisfacts.data.repository.FactsRepositoryImpl
import com.example.chucknorrisfacts.domain.repositories.FactsRepository
import com.example.chucknorrisfacts.domain.usecases.GetCategories
import com.example.chucknorrisfacts.domain.usecases.GetRandomFact
import com.example.chucknorrisfacts.presentation.viewmodel.CategoriesViewModel
import com.example.chucknorrisfacts.presentation.viewmodel.FactDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoriesModule = module {
    viewModel {
        CategoriesViewModel(getCategories = get())
    }
    single { GetCategories(repository = get()) }
}

val factDetailsModule = module {
    viewModel {
        FactDetailsViewModel(getRandomFact = get())
    }
    single { GetRandomFact(repository = get()) }
}

val coreModule = module {
    //repositories
    single<FactsRepository> { FactsRepositoryImpl( service = get()) }
    //data sources
    factory<FactsService> { provideApiService(get(), FactsService::class.java) }
    single { provideRetrofitInstance() }
}


