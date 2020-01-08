package com.example.movieapp.data.api

import com.example.movieapp.data.vo.MovieDetails
import com.example.movieapp.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    //https://api.themoviedb.org/3/movie/popular?api_key=f1477bc4443adc2a5f6762f80109be48&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/10000?api_key=f1477bc4443adc2a5f6762f80109be48&language=en-US
    //https://api.themoviedb.org/3/

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id : Int) : Single<MovieDetails>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page : Int) : Single<MovieResponse>
}