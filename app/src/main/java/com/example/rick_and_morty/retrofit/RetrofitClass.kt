package com.example.rick_and_morty.retrofit

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RetrofitClass: Callback<RetrofitData> {

    val characterResult= MutableLiveData<RetrofitData>()

    fun getCharacterResult() {
        Common.retrofitService.getCharacter().enqueue(this)
    }

        override fun onResponse(call: Call<RetrofitData>, response: Response<RetrofitData>) {
            if (response.isSuccessful){
                characterResult.postValue(response.body())
            }
        }

        override fun onFailure(call: Call<RetrofitData>, t: Throwable) {

        }

}