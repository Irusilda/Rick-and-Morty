package com.example.rick_and_morty

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showCharacterFragment()

    fun showLocationsFragment()

    fun showEpisodesFragment()

    fun backPressed()

    fun showDetailCharacterFragment()


}