package com.evgeniykim.expresstest2.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.evgeniykim.expresstest2.models.MovieDetails
import com.evgeniykim.expresstest2.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieView(private val movieRepository: SingleMovieRepository, movieId: Int) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<MovieDetails> = movieRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    val networkState: LiveData<NetworkState> = movieRepository.getMovieDetailsNetworkState()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}