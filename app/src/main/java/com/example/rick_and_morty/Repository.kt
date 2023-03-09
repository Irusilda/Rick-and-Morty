package com.example.rick_and_morty

import com.example.rick_and_morty.retrofit.CharacterData
import com.example.rick_and_morty.retrofit.EpisodeData
import com.example.rick_and_morty.retrofit.ResultEpisode
import io.reactivex.Single

interface Repository {

    fun searchValue(value: String): Single<CharacterData>

    fun searchEpisode(): Single<EpisodeData>

    fun searchEpisodeForCharacter(ids: Int): Single<ResultEpisode>
}