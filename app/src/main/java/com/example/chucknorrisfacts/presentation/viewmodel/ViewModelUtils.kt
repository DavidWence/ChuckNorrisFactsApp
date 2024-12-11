package com.example.chucknorrisfacts.presentation.viewmodel

import com.example.chucknorrisfacts.R
import com.example.chucknorrisfacts.domain.entity.ErrorDescription
import com.example.chucknorrisfacts.presentation.ChuckNorrisFactsApp

fun ErrorDescription.asText(): String =
    when(this){
        ErrorDescription.Unknown ->
            ChuckNorrisFactsApp.getString(R.string.error_unknown)
        ErrorDescription.ConnectionFailure ->
            ChuckNorrisFactsApp.getString(R.string.error_connection_failure)
        ErrorDescription.MissingResponse ->
            ChuckNorrisFactsApp.getString(R.string.error_missing_information)
    }