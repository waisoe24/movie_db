package com.example.moviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class TopRatedAdapter(var movieList: List<Result> = ArrayList()):RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>(){

    inner class TopRatedViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)
    {
        fun bindMovie(result: Result){
            
            itemView.movieText.text = result.title
            
            var baseUrl = "https://image.tmdb.org/t/p/w500"
            Picasso.get().load(baseUrl+result.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.movieImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        var viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return TopRatedViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bindMovie(movieList[position])

    }
    
    fun updateTopRated(TopRatedList: List<Result>){
        this.movieList = TopRatedList
        notifyDataSetChanged()
    }
}