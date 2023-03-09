package com.example.rick_and_morty.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceAPI {
    @GET("{value}")
    fun getValue(@Path("value") query: String): Single<CharacterData>

    @GET("episode")
    fun getEpisodes(): Single<EpisodeData>
}
