package com.hhi.searchmovieproject.data.remote

import com.hhi.searchmovieproject.api.NaverAPI
import com.hhi.searchmovieproject.data.model.MovieData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class NaverRemoteDataSourceImpl @Inject constructor(
    private val api: NaverAPI
) : NaverRemoteDataSource {
    override fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        api.searchMovies(query).enqueue(object : retrofit2.Callback<MovieData.Response> {
            override fun onResponse(
                call: Call<MovieData.Response>,
                response: Response<MovieData.Response>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        success(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieData.Response>, t: Throwable) {
                failed(t)
            }
        })
    }
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class NaverRemoteDataModule {
    @Binds
    @Singleton
    abstract fun bindNaverRemoteData(naverRemoteDataSourceImpl: NaverRemoteDataSourceImpl): NaverRemoteDataSource
}