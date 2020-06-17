package com.example.moviedb.api

import com.example.moviedb.model.TopRated
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    private val movieInterface:MovieInterface

    companion object{
       const val BASE_URL="https://api.themoviedb.org/3/movie/"
    }

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieInterface = retrofit.create(MovieInterface::class.java)
    }

    fun getTopRated(api_key: String): Call<TopRated> = movieInterface.getTopRated(api_key)
}