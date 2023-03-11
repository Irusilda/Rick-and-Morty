package com.example.rick_and_morty.retrofit

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("{value}")
    fun getValue(@Path("value") query: String): Single<CharacterData>

    @GET("episode")
    fun getEpisodes(): Single<EpisodeData>

    @GET("episode/{id}")
    fun getEpisodesForCharacter(@Path("id") id: Int): Single<ResultEpisode>

    @GET("episode/{id}")
    fun getEpisodesFor(@Path("id") id: Int): Call<ResultEpisode>
}
