package com.example.chucknorrisfacts.presentation

import android.app.Application
import androidx.annotation.StringRes
import com.example.chucknorrisfacts.di.categoriesModule
import com.example.chucknorrisfacts.di.coreModule
import com.example.chucknorrisfacts.di.factDetailsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChuckNorrisFactsApp: Application() {

    companion object{
        private lateinit var instance: ChuckNorrisFactsApp

        fun getString(@StringRes stringId: Int) = instance.resources.getString(stringId)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@ChuckNorrisFactsApp)
            modules(listOf(coreModule, categoriesModule, factDetailsModule))
        }
    }

}