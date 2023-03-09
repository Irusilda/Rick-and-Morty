package com.example.rick_and_morty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rick_and_morty.CustomTitle
import com.example.rick_and_morty.R
import com.example.rick_and_morty.navigator

class LocationsFragment : Fragment(), CustomTitle {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        navigator().backPressed()

        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = LocationsFragment()
    }

    override fun getTitleRes(): Int = R.string.locations
}