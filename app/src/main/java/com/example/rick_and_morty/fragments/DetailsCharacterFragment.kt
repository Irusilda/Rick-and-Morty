package com.example.rick_and_morty.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rick_and_morty.EpisodesAdapter
import com.example.rick_and_morty.MyRepositoryProvider
import com.example.rick_and_morty.OnEpisodeClickListener
import com.example.rick_and_morty.databinding.FragmentDetailsCharacterBinding
import com.example.rick_and_morty.retrofit.ResultCharacter
import com.example.rick_and_morty.retrofit.ResultEpisode
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsCharacterFragment : Fragment(), OnEpisodeClickListener {

    lateinit var binding: FragmentDetailsCharacterBinding
    lateinit var adapter: EpisodesAdapter
    var newList: MutableList<ResultEpisode> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsCharacterBinding.inflate(inflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments?.getParcelable<ResultCharacter>("KEY")!!
        binding.apply {

            Picasso.get().load(item.image).into(imageCharDetails)
            nameCharDetails.text = item.name
            statusCharDetails.text = item.status
            speciesCharDetails.text = item.species
            type.text = item.type
            genderCharDetails.text = item.gender
            origin.text = item.origin.name
            location.text = item.location.name

            val list = item.episode
            val listOfId = mutableListOf<Int>()
            for (i in list) {
                val addItem = i.substringAfterLast("/")
                listOfId.add(addItem.toInt())
            }
            Log.d("MyLog", "$listOfId")
            getResult(listOfId)
        }
    }

    @SuppressLint("CheckResult")
    private fun getResult(listOfId: List<Int>) {

        Observable.fromIterable(listOfId)
            .flatMapSingle { it ->
                MyRepositoryProvider.provideMyRepository().searchEpisodeForCharacter(it)
            }.toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { ids ->
                newList = ids
                adapter = EpisodesAdapter(this@DetailsCharacterFragment).apply {
                    itemEpisodesList = newList
                }
                binding.detailsRecycler.adapter = adapter
            }
    }

    companion object {


        @JvmStatic

        fun newInstance(item: ResultCharacter): DetailsCharacterFragment {
            val args = Bundle().apply {
                putParcelable("KEY", item)
            }

            val fragment = DetailsCharacterFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun oEpisodeClick(item: ResultEpisode) {

    }


}