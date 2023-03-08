package com.example.rick_and_morty.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rick_and_morty.*
import com.example.rick_and_morty.databinding.FragmentCharacterBinding
import com.example.rick_and_morty.retrofit.RetrofitData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class CharacterFragment : Fragment(), CustomTitle {

    lateinit var binding: FragmentCharacterBinding
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
        getResult()

    }

    companion object {

        @JvmStatic
        fun newInstance() = CharacterFragment()
    }

    override fun getTitleRes(): Int = R.string.characters

    private fun getResult() {
        val observer = object : SingleObserver<RetrofitData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(itemList: RetrofitData) {
                adapter = CharacterAdapter(itemList)
                binding.characterRecycler.adapter = adapter
            }

            override fun onError(e: Throwable) {
                Toast.makeText(context, "e", Toast.LENGTH_LONG).show()
            }
        }
        MyRepositoryProvider.provideMyRepository().searchValue("character")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}