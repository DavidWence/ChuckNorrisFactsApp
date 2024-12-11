package com.example.chucknorrisfacts.domain.usecases

import com.example.chucknorrisfacts.domain.repositories.FactsRepository

class GetCategories(private val repository: FactsRepository) {

    suspend operator fun invoke() = repository.getCategories()

}