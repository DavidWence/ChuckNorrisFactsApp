package com.example.chucknorrisfacts.domain.usecases

import com.example.chucknorrisfacts.domain.entity.Category
import com.example.chucknorrisfacts.domain.repositories.FactsRepository

class GetRandomFact(private val repository: FactsRepository) {

    suspend operator fun invoke(category: Category) = repository.getRandomFact(category)

}