package com.example.rick_and_morty.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.rick_and_morty.databinding.FragmentDetailsCharacterBinding
import com.example.rick_and_morty.retrofit.ResultCharacter
import com.squareup.picasso.Picasso

class DetailsCharacterFragment : Fragment() {
    lateinit var binding: FragmentDetailsCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentDetailsCharacterBinding.inflate(inflater)
        Log.d("MyLog", "Details show")

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
//            detailsRecycler.adapter = EpisodesAdapter(this@DetailsCharacterFragment)
    }
    }

    companion object {


        @JvmStatic
//        fun newInstance(bundle: Bundle) = DetailsCharacterFragment()
        fun newInstance(item: ResultCharacter): DetailsCharacterFragment {
val args = Bundle().apply {
    putParcelable("KEY", item)
}

            val fragment = DetailsCharacterFragment()
            fragment.arguments = args
            return fragment
        }

    }


}