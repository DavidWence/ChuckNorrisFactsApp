package com.example.chucknorrisfacts.domain.entity

// class to manage results from transactions in repositories
sealed class Outcome<out T> {
    data class Success<out T>(val data: T): Outcome<T>()
    data class Error(val description: ErrorDescription = ErrorDescription.Unknown): Outcome<Nothing>()
    data object Completed: Outcome<Nothing>()

    val value: T? get() = if(this is Success) data else null
}

fun <T> T?.asOutcome(descriptionIfError: ErrorDescription = ErrorDescription.Unknown): Outcome<T> {
    return if (this != null)
        Outcome.Success(this)
    else
        Outcome.Error(descriptionIfError)
}

inline fun outcomeCompleted(f: () -> Unit): Outcome<Nothing> {
    f()
    return Outcome.Completed
}

fun areAllOutcomesValid(vararg outcomes: Outcome<Any>): Boolean {
    for (outcome in outcomes)
        if (outcome is Outcome.Error)
            return false
    return true
}