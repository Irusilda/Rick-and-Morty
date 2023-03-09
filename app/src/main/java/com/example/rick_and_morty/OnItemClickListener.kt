package com.example.rick_and_morty

import com.example.rick_and_morty.retrofit.ResultCharacter
import com.example.rick_and_morty.retrofit.ResultEpisode


interface OnItemClickListener {
    fun onItemClick(item: ResultCharacter)


}
interface OnEpisodeClickListener {
    fun oEpisodeClick(item: ResultEpisode)


}