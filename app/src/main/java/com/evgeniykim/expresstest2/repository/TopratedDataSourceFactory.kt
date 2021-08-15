package com.evgeniykim.expresstest2.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.evgeniykim.expresstest2.api.MovieDbInterface
import com.evgeniykim.expresstest2.models.Movie
import io.reactivex.disposables.CompositeDisposable

class TopratedDataSourceFactory (private val apiService: MovieDbInterface, private val compositeDisposable: CompositeDisposable) :
    DataSource.Factory<Int, Movie>(){

    val movieLiveDataSource = MutableLiveData<TopratedDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val topratedDataSource = TopratedDataSource(apiService, compositeDisposable)

        movieLiveDataSource.postValue(topratedDataSource)
        return topratedDataSource
    }
}