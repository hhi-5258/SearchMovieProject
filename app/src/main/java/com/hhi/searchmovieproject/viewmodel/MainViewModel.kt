package com.hhi.searchmovieproject.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hhi.searchmovieproject.base.BaseViewModel
import com.hhi.searchmovieproject.data.model.MovieData
import com.hhi.searchmovieproject.data.repository.NaverRepositoryDataSourceImpl

class MainViewModel @ViewModelInject constructor(
    private val repositoryDataSourceImpl: NaverRepositoryDataSourceImpl
) : BaseViewModel() {
    private val _movieList = MutableLiveData<ArrayList<MovieData.MovieItem>>()
    val movieList: LiveData<ArrayList<MovieData.MovieItem>> = _movieList
    private val _emptyQueryEvent = MutableLiveData<Unit>()
    val emptyQueryEvent: LiveData<Unit> = _emptyQueryEvent
    private val _hideKeyBoardEvent = MutableLiveData<Unit>()
    val hideKeyBoardEvent: LiveData<Unit> = _hideKeyBoardEvent
    private val _searchRecentQueryEvent = MutableLiveData<Unit>()
    val searchRecentQueryEvent: LiveData<Unit> = _searchRecentQueryEvent
    val query = MutableLiveData<String>()

    fun searchMovie() {
        _hideKeyBoardEvent.value = Unit
        if (query.value.isNullOrBlank()) {
            _emptyQueryEvent.value = Unit
        } else {

            repositoryDataSourceImpl.saveQuery(query.value!!)

            visible.value = true
            repositoryDataSourceImpl.searchMovies(query.value!!,
                success = {
                    _movieList.value = it.items
                    visible.value = false
                },
                failed = {
                    Log.e("search_failed", it.toString())
                    visible.value = false
                }
            )
        }
    }

    fun searchRecentQuery() {
        _searchRecentQueryEvent.value = Unit
    }
}