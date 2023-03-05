package com.example.rick_and_morty

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.databinding.ItemCharacterBinding
import com.example.rick_and_morty.retrofit.RetrofitData


import com.squareup.picasso.Picasso

class CharacterAdapter (private val context: Context, private var itemCharacterData: RetrofitData) : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {


    class MyViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = itemCharacterData.results[position]
        with(holder.binding) {
            val text = context.getString(R.string.character_name_title) + item.name
            nameCharacter.text = text

            Picasso.get().load(item.image).into(imageCharacter)
        }


    }

    override fun getItemCount(): Int = itemCharacterData.results.size
}