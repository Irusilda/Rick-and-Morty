package com.example.rick_and_morty

import com.example.rick_and_morty.retrofit.RetrofitData
import io.reactivex.Single

interface Repository {
    fun searchValue(value: String): Single<RetrofitData>
}