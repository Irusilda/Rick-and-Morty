package com.example.rick_and_morty.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceCharacter {
    @GET("{value}")
    fun getValue(@Path("value") query: String): Single <RetrofitData>
}