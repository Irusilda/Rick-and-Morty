package com.example.rick_and_morty

import androidx.fragment.app.Fragment
import com.example.rick_and_morty.retrofit.ResultCharacter

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showCharacterFragment()

    fun showLocationsFragment()

    fun showEpisodesFragment()

    fun backPressed()

    fun showDetailCharacterFragment(details: ResultCharacter)


}