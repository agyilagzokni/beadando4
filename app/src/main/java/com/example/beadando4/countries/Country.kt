package com.example.beadando4.countries

import com.squareup.moshi.Json

data class Country(
    @Json(name = "name") val name: Name,
    @Json(name = "unMember") val unMember: Boolean,
    /*@Json(name = "capital") val capital: List<String>,
    @Json(name = "population") val population: Int,
    @Json(name = "region") val region: String*/
)
data class Name(
    @Json(name = "common") val name: String
)