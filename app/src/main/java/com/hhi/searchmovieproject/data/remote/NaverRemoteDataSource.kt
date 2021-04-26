package com.hhi.searchmovieproject.data.remote

import com.hhi.searchmovieproject.data.model.MovieData

interface NaverRemoteDataSource {
    fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    )
}