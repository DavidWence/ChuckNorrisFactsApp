package com.example.chucknorrisfacts.domain.repositories

import com.example.chucknorrisfacts.domain.entity.Category
import com.example.chucknorrisfacts.domain.entity.Fact
import com.example.chucknorrisfacts.domain.entity.Outcome

interface FactsRepository {

    suspend fun getCategories(): Outcome<List<Category>>
    suspend fun getRandomFact(category: Category): Outcome<Fact>

}