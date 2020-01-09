package com.example.movieapp.ui.popular_movies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.data.api.TheMovieDBClient
import com.example.movieapp.data.repository.NetworkState
import com.example.movieapp.ui.login.LoggedActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LoggedActivity() {

    private lateinit var viewModel : MainActivityViewModel

    lateinit var movieRepository : MoviePagedListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = TheMovieDBClient.getClient()

        movieRepository = MoviePagedListRepository(apiService)
        viewModel = getViewModel()
        val movieAdapter = PopularMoviesPagedListAdapter(this)

        movie_recycler_view.layoutManager = LinearLayoutManager(this)
        movie_recycler_view.setHasFixedSize(true)
        movie_recycler_view.adapter = movieAdapter

        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if(viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if(viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if(!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })
    }

    private fun getViewModel() : MainActivityViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainActivityViewModel(movieRepository) as T
            }

        })[MainActivityViewModel::class.java]
    }
}
