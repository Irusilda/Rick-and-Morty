package com.example.rick_and_morty.retrofit

object Common {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    val retrofitService: ServiceCharacter
        get() = RetrofitClient.getClient(BASE_URL).create(ServiceCharacter::class.java)
}