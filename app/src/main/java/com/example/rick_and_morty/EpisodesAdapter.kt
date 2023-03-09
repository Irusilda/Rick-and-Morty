package com.example.rick_and_morty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.databinding.ItemEpisodeBinding
import com.example.rick_and_morty.retrofit.EpisodeData
import com.example.rick_and_morty.retrofit.ResultEpisode

class EpisodesAdapter( private val listener: OnEpisodeClickListener?) :
    RecyclerView.Adapter<EpisodesAdapter.MyViewHolder>() {

    lateinit var itemEpisodesList : EpisodeData

    class MyViewHolder(val binding: ItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (item: ResultEpisode, listener: OnEpisodeClickListener?){
            itemView.setOnClickListener {
                listener?.oEpisodeClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.binding){
            val item = itemEpisodesList.results[position]
            episodeName.text = item.name
            episodeNumber.text = item.episode
            episodeAirDate.text = item.air_date
            holder.bind(item, listener)

        }
    }

    override fun getItemCount(): Int = itemEpisodesList.results.size
}