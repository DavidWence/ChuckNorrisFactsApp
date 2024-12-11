package com.example.chucknorrisfacts.domain.entity

sealed class ErrorDescription {

    data object Unknown: ErrorDescription()
    data object MissingResponse: ErrorDescription()
    data object ConnectionFailure: ErrorDescription()

}