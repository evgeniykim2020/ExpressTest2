package com.evgeniykim.expresstest2.ui.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evgeniykim.expresstest2.api.MovieDbInterface
import com.evgeniykim.expresstest2.models.Movie
import com.evgeniykim.expresstest2.repository.*
import com.evgeniykim.expresstest2.utils.Constants
import io.reactivex.disposables.CompositeDisposable

class TopratedMoviePagedListRepository(private val apiService: MovieDbInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var topratedDataSourceFactory: TopratedDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        topratedDataSourceFactory = TopratedDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(topratedDataSourceFactory, config).build()
        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<TopratedDataSource, NetworkState>(
            topratedDataSourceFactory.movieLiveDataSource, TopratedDataSource::networkState
        )
    }
}