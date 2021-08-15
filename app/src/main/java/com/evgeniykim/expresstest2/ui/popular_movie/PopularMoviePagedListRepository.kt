package com.evgeniykim.expresstest2.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evgeniykim.expresstest2.api.MovieDbInterface
import com.evgeniykim.expresstest2.models.Movie
import com.evgeniykim.expresstest2.repository.MovieDataSource
import com.evgeniykim.expresstest2.repository.MovieDataSourceFactory
import com.evgeniykim.expresstest2.repository.NetworkState
import com.evgeniykim.expresstest2.utils.Constants
import io.reactivex.disposables.CompositeDisposable

class PopularMoviePagedListRepository(private val apiService: MovieDbInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()
        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.movieLiveDataSource, MovieDataSource::networkState
        )
    }
}