package com.example.rick_and_morty.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ServiceCharacter {
    @GET("character")
    fun getCharacter(): Call <RetrofitData>
}