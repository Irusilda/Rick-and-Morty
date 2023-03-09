package com.example.rick_and_morty


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.databinding.ItemCharacterBinding
import com.example.rick_and_morty.retrofit.CharacterData
import com.example.rick_and_morty.retrofit.ResultCharacter
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private val listener: OnItemClickListener?

) : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    lateinit var itemCharacterData: CharacterData

    class MyViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultCharacter, listener: OnItemClickListener?) {
            itemView.setOnClickListener {
                listener?.onItemClick(item)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = itemCharacterData.results[position]
        with(holder.binding) {
            nameCharacter.text = item.name
            speciesCharacter.text = item.species
            statusCharacter.text = item.status
            genderCharacter.text = item.gender
            holder.bind(item, listener)


            Picasso.get().load(item.image).into(imageCharacter)
        }


    }

    override fun getItemCount(): Int = itemCharacterData.results.size
}