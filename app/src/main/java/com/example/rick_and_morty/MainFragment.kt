package com.example.rick_and_morty

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyLog", "Fragment")
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }

}