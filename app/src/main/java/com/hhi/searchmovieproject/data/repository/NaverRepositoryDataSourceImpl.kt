package com.hhi.searchmovieproject.data.repository

import com.hhi.searchmovieproject.data.local.NaverLocalDataSource
import com.hhi.searchmovieproject.data.model.MovieData
import com.hhi.searchmovieproject.data.remote.NaverRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class NaverRepositoryDataSourceImpl @Inject constructor(
    private val naverRemoteDataSource: NaverRemoteDataSource,
    private val naverLocalDataSource: NaverLocalDataSource

) : NaverRepositoryDataSource {

    override fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        naverRemoteDataSource.searchMovies(query, success, failed)
    }

    override fun saveQuery(query: String) {
        naverLocalDataSource.saveQuery(query)
    }

    override fun getQueryList(): List<String> = naverLocalDataSource.getQueryList()
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class NaverRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNaverRepository(naverRepositoryDataSourceImpl: NaverRepositoryDataSourceImpl): NaverRepositoryDataSource
}