package com.evgeniykim.expresstest2.ui.movie_details

import androidx.lifecycle.LiveData
import com.evgeniykim.expresstest2.api.MovieDbInterface
import com.evgeniykim.expresstest2.models.MovieDetails
import com.evgeniykim.expresstest2.repository.MovieDetailsNetworkDataSource
import com.evgeniykim.expresstest2.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieRepository(private val apiService: MovieDbInterface) {
    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable, movieId: Int) :
            LiveData<MovieDetails> {
        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieDetailsResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}