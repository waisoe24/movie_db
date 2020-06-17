package com.example.moviedb.ui

import android.view.ActionMode
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.api.MovieApi
import com.example.moviedb.model.Result
import com.example.moviedb.model.TopRated
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewModel :ViewModel(){
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var result: MutableLiveData<List<Result>> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()

    fun getResult() :LiveData<List<Result>> = result
    fun getError() : LiveData<Boolean> = error

    private val movieApi: MovieApi = MovieApi()

    fun loadResult(){

        loading.value = true
        var apiCall = movieApi.getTopRated("b39295a400e327e0b2a2e5686a7cd31b")
        apiCall.enqueue(object :Callback<TopRated>
        {
            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                loading.value = false
                error.value = true
            }

            override fun onResponse(call: Call<TopRated>, response: Response<TopRated>) {
                var topRatedList = response.body()?.results?: emptyList()
                result.value = topRatedList

            }

        })
    }

}