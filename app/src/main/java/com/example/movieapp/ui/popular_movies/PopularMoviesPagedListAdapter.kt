package com.example.movieapp.ui.popular_movies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.api.POSTER_BASE_URL
import com.example.movieapp.data.repository.NetworkState
import com.example.movieapp.data.vo.Movie
import com.example.movieapp.ui.movie_details.SingleMovie
import kotlinx.android.synthetic.main.activity_single_movie.view.*
import kotlinx.android.synthetic.main.movie_list_item.view.*
import kotlinx.android.synthetic.main.movie_list_item.view.movie_release_date
import kotlinx.android.synthetic.main.movie_list_item.view.movie_title
import kotlinx.android.synthetic.main.network_state_item.view.*
import kotlinx.android.synthetic.main.network_state_item.view.progress_bar
import kotlinx.android.synthetic.main.network_state_item.view.txt_error

class PopularMoviesPagedListAdapter(public val context : Context) : PagedListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState : NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view : View
        return if(viewType == MOVIE_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
            MovieItemViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            NetworkStateItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == MOVIE_VIEW_TYPE) {
            (holder as MovieItemViewHolder).bind(getItem(position), context)
        } else {
            (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }

    fun hasExtraRow() : Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if(hasExtraRow != hadExtraRow) {
            if(hadExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if(hasExtraRow && previousState != this.networkState)
            notifyItemChanged(itemCount - 1)
    }

    override fun getItemViewType(position: Int): Int {

        return if(hasExtraRow() && position == itemCount - 1 )
            NETWORK_VIEW_TYPE
        else
            MOVIE_VIEW_TYPE
    }


}



    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    class MovieItemViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(movie : Movie?, context: Context) {
            itemView.movie_title.text = movie?.title
            itemView.movie_release_date.text = movie?.releaseDate
            val moviePosterURL = POSTER_BASE_URL + movie?.posterPath
            Glide.with(itemView.context).load(moviePosterURL).into(itemView.movie_poster)
            itemView.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, SingleMovie::class.java)
                intent.putExtra("movie_id", movie?.id)
                context.startActivity(intent)
            })
        }
    }

    class NetworkStateItemViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(networkState : NetworkState?) {
            if(networkState != null && networkState == NetworkState.LOADING) {
                itemView.progress_bar.visibility = View.VISIBLE
            } else {
                itemView.progress_bar.visibility = View.GONE
            }
            if(networkState != null && (networkState == NetworkState.ERROR || networkState == NetworkState.ENDOFLIST)) {
                itemView.txt_error.visibility = View.VISIBLE
                itemView.txt_error.text = networkState.msg
            } else {
                itemView.txt_error.visibility = View.GONE
            }
        }

    }
