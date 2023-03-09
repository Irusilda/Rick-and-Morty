package com.example.rick_and_morty

import com.example.rick_and_morty.retrofit.CharacterData
import com.example.rick_and_morty.retrofit.EpisodeData
import com.example.rick_and_morty.retrofit.ResultEpisode
import com.example.rick_and_morty.retrofit.ServiceAPI
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RepositoryImpl(private val newSearch: ServiceAPI): Repository {
    override fun searchValue(value: String): Single<CharacterData> {
        return newSearch.getValue(value)
            .subscribeOn(Schedulers.io())

    }

    override fun searchEpisode(): Single<EpisodeData> {
        return newSearch.getEpisodes()
            .subscribeOn(Schedulers.io())
    }

    override fun searchEpisodeForCharacter(ids: Int): Single<ResultEpisode> {
        return newSearch.getEpisodesForCharacter(ids)
    }
}
