package com.example.rick_and_morty

import com.example.rick_and_morty.retrofit.RetrofitData
import com.example.rick_and_morty.retrofit.ServiceCharacter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RepositoryImpl(private val newSearch: ServiceCharacter): Repository {
    override fun searchValue(value: String): Single<RetrofitData> {
        return newSearch.getValue(value)
            .subscribeOn(Schedulers.io())

    }
}