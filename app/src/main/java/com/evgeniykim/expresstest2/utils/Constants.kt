package com.evgeniykim.expresstest2.utils

import com.evgeniykim.expresstest2.BuildConfig

object Constants {

    const val API_KEY = BuildConfig.MOVIES_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
    const val FIRST_PAGE = 1
    const val POST_PER_PAGE = 20

}