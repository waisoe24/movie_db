package com.example.moviedb.api

import com.example.moviedb.model.TopRated
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {
    @GET("top_rated")
    fun getTopRated(@Query ("api_key") apikey:String):Call<TopRated>
}