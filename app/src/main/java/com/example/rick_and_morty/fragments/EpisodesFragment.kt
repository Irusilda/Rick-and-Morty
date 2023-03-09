package com.example.rick_and_morty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rick_and_morty.*
import com.example.rick_and_morty.databinding.FragmentEpisodsBinding
import com.example.rick_and_morty.retrofit.EpisodeData
import com.example.rick_and_morty.retrofit.ResultEpisode
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class EpisodesFragment : Fragment(), CustomTitle, OnEpisodeClickListener {

    lateinit var binding: FragmentEpisodsBinding
    lateinit var adapter: EpisodesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        navigator().backPressed()
        binding = FragmentEpisodsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getResult()
    }

    private fun getResult() {
        val observer = object : SingleObserver<EpisodeData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(itemList: EpisodeData) {
                adapter = EpisodesAdapter(this@EpisodesFragment).apply {
                    itemEpisodesList = itemList
                }
                binding.episodeMainRecycler.adapter = adapter
            }

            override fun onError(e: Throwable) {
                Toast.makeText(context, "$e", Toast.LENGTH_LONG).show()
            }
        }
        MyRepositoryProvider.provideMyRepository().searchEpisode()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }

    companion object {

        @JvmStatic
        fun newInstance() = EpisodesFragment()
    }

    override fun getTitleRes(): Int = R.string.episodes

    override fun oEpisodeClick(item: ResultEpisode) {

    }


}