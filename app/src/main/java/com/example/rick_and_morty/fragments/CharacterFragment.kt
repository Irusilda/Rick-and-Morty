package com.example.rick_and_morty.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rick_and_morty.*
import com.example.rick_and_morty.databinding.FragmentCharacterBinding
import com.example.rick_and_morty.retrofit.Common
import com.example.rick_and_morty.retrofit.RetrofitData
import com.example.rick_and_morty.retrofit.ServiceCharacter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterFragment : Fragment(), CustomTitle {
    lateinit var binding: FragmentCharacterBinding
    var mService: ServiceCharacter = Common.retrofitService
    lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        navigator().backPressed()
        binding = FragmentCharacterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mService.getCharacter().enqueue(object : Callback<RetrofitData> {

            override fun onResponse(
                call: Call<RetrofitData>,
                response: Response<RetrofitData>
            ) {
                adapter = context?.let { CharacterAdapter(it, response.body()!!) }!!
                binding.characterRecycler.adapter = adapter
            }

            override fun onFailure(call: Call<RetrofitData>, t: Throwable) {
                Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_LONG).show()
            }

        })
//        val retr = RetrofitClass()
//        retr.getCharacterResult()
//        val result = retr.characterResult
//
//        adapter = context?.let { CharacterAdapter(it, result) }!!
//        binding.characterRecycler.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() = CharacterFragment()
    }

    override fun getTitleRes(): Int = R.string.characters
}