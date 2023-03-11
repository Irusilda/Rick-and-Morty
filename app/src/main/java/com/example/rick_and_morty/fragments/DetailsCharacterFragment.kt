package com.example.rick_and_morty.fragments

import android.annotation.SuppressLint
import android.os.Build
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

private const val CHARACTER_ITEM_KEY = "key"

class DetailsCharacterFragment : Fragment(), OnEpisodeClickListener {



    lateinit var binding: FragmentDetailsCharacterBinding
    lateinit var adapter: EpisodesAdapter
    lateinit var characterItem: ResultCharacter
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

        characterItem = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable<ResultCharacter>(CHARACTER_ITEM_KEY)!!
        } else {
            arguments?.getParcelable(CHARACTER_ITEM_KEY, ResultCharacter::class.java)!!

        }
        binding.apply {

            Picasso.get().load(characterItem.image).into(imageCharDetails)
            nameCharDetails.text = characterItem.name
            statusCharDetails.text = characterItem.status
            speciesCharDetails.text = characterItem.species
            type.text = characterItem.type
            genderCharDetails.text = characterItem.gender
            origin.text = characterItem.origin.name
            location.text = characterItem.location.name

            val list = characterItem.episode
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
                putParcelable(CHARACTER_ITEM_KEY, item)
            }

            val fragment = DetailsCharacterFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun oEpisodeClick(item: ResultEpisode) {

    }


}