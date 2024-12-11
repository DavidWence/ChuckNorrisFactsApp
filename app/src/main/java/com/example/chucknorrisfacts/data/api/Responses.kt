package com.example.chucknorrisfacts.data.api

import com.example.chucknorrisfacts.domain.entity.Fact
import com.squareup.moshi.Json

data class FactResponse(
    @Json(name = "value") val description: String? = null,
    @Json(name = "icon_url") val icon: String? = null){

    fun toDomain() = Fact(description = description ?: "", iconUrl = icon ?: "")

}