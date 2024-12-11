package com.example.chucknorrisfacts.data.repository

import com.example.chucknorrisfacts.data.api.ApiDataSource
import com.example.chucknorrisfacts.data.api.FactResponse
import com.example.chucknorrisfacts.data.api.FactsService
import com.example.chucknorrisfacts.domain.entity.Category
import com.example.chucknorrisfacts.domain.entity.Fact
import com.example.chucknorrisfacts.domain.entity.asOutcome
import com.example.chucknorrisfacts.domain.repositories.FactsRepository

class FactsRepositoryImpl(private val service: FactsService): FactsRepository {

    override suspend fun getCategories() = object: ApiDataSource<List<String>, List<Category>>(){
        override suspend fun consumeService() =
            service.getCategories()

        override fun getOutcome(body: List<String>) = body.map { Category(it) }.asOutcome()
    }.consume()

    override suspend fun getRandomFact(category: Category) = object: ApiDataSource<FactResponse, Fact>(){
        override suspend fun consumeService() =
            service.getRandomByCategory(category.label)

        override fun getOutcome(body: FactResponse) = body.toDomain().asOutcome()
    }.consume()

}