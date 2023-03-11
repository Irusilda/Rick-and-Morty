package com.example.rick_and_morty.retrofit

data class EpisodeData(
    val info: Info,
    val results: List<ResultEpisode>
)

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)

data class ResultEpisode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)