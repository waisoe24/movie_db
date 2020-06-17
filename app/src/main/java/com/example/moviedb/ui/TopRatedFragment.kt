package com.example.moviedb.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.R
import com.example.moviedb.adapter.TopRatedAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

class TopRatedFragment : Fragment() {

    private lateinit var topRatedViewModel: TopRatedViewModel
    private lateinit var topRatedAdapter: TopRatedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       topRatedViewModel = ViewModelProviders.of(this).get(TopRatedViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_movie, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieRecyclerview.layoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false)
        topRatedAdapter = TopRatedAdapter()
        movieRecyclerview.adapter = topRatedAdapter
        observeViewModel()

    }

    fun observeViewModel(){
        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        topRatedViewModel.loadResult()

        topRatedViewModel.getResult().observe(viewLifecycleOwner, Observer {
            movieRecyclerview.visibility = View.VISIBLE
            txtError.visibility = View.GONE
            topRatedAdapter.updateTopRated(it)
        })

        topRatedViewModel.getError().observe(viewLifecycleOwner, Observer {
            movieRecyclerview.visibility = View.GONE
            txtError.visibility = View.VISIBLE
        })
    }

}