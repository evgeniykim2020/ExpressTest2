package com.evgeniykim.expresstest2.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.evgeniykim.expresstest2.models.Movie
import com.evgeniykim.expresstest2.repository.NetworkState
import com.evgeniykim.expresstest2.ui.toprated.TopratedMoviePagedListRepository
import io.reactivex.disposables.CompositeDisposable

class UpcomingViewModel(private val movieRepository: UpcomingPagedListRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePagedList: LiveData<PagedList<Movie>> = movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    val networkState: LiveData<NetworkState> = movieRepository.getNetworkState()

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty()?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}