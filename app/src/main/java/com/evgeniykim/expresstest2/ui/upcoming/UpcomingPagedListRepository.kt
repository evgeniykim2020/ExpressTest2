package com.evgeniykim.expresstest2.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evgeniykim.expresstest2.api.MovieDbInterface
import com.evgeniykim.expresstest2.models.Movie
import com.evgeniykim.expresstest2.repository.*
import com.evgeniykim.expresstest2.utils.Constants
import io.reactivex.disposables.CompositeDisposable

class UpcomingPagedListRepository(private val apiService: MovieDbInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var upcomingDataFactory: UpcomingDataFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        upcomingDataFactory = UpcomingDataFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(upcomingDataFactory, config).build()
        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<UpcomingDataSource, NetworkState>(
            upcomingDataFactory.movieLiveDataSource, UpcomingDataSource::networkState
        )
    }
}