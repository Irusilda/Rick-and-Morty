package com.example.rick_and_morty

import com.example.rick_and_morty.retrofit.ServiceApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MyRepositoryProvider {
    fun provideMyRepository(): Repository {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ServiceApi::class.java)
        return RepositoryImpl (retrofit)
    }
}