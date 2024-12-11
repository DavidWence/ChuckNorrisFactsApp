package com.example.chucknorrisfacts.data.api

import com.example.chucknorrisfacts.data.handleThrowable
import com.example.chucknorrisfacts.data.withIO
import com.example.chucknorrisfacts.domain.entity.ErrorDescription
import com.example.chucknorrisfacts.domain.entity.Outcome
import retrofit2.Response

// class to properly handle api service consumption
// Remote: object from server, Domain: object in domain layer
abstract class ApiDataSource<Remote: Any, Domain: Any> {

    suspend fun consume(): Outcome<Domain> {
        return try {
            val response = withIO { consumeService() }
            if (response.isSuccessful) {
                val body = response.body()
                if(body != null)
                    getOutcome(body)
                else
                    Outcome.Error(ErrorDescription.MissingResponse)
            } else
                Outcome.Error()
        } catch (t: Throwable) {
            t.printStackTrace()
            Outcome.Error(handleThrowable(t))
        }
    }

    protected abstract suspend fun consumeService(): Response<Remote>

    protected abstract fun getOutcome(body: Remote): Outcome<Domain>

}