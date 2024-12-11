package com.example.chucknorrisfacts.data

import com.example.chucknorrisfacts.domain.entity.ErrorDescription
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.cert.CertPathValidatorException

suspend fun <T> withIO(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.IO, block)

fun handleThrowable(t: Throwable): ErrorDescription = when(t){
    is SocketTimeoutException, is ConnectException, is UnknownHostException,
    is CertPathValidatorException -> ErrorDescription.ConnectionFailure
    else -> ErrorDescription.Unknown
}