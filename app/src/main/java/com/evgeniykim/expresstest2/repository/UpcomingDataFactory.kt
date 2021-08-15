package com.evgeniykim.expresstest2.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.evgeniykim.expresstest2.api.MovieDbInterface
import com.evgeniykim.expresstest2.models.Movie
import io.reactivex.disposables.CompositeDisposable

class UpcomingDataFactory (private val apiService: MovieDbInterface, private val compositeDisposable: CompositeDisposable) :
    DataSource.Factory<Int, Movie>(){

    val movieLiveDataSource = MutableLiveData<UpcomingDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val upcomingDataSource = UpcomingDataSource(apiService, compositeDisposable)

        movieLiveDataSource.postValue(upcomingDataSource)
        return upcomingDataSource
    }
}