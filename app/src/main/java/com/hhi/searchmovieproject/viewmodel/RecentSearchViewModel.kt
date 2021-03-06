package com.hhi.searchmovieproject.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hhi.searchmovieproject.base.BaseViewModel
import com.hhi.searchmovieproject.data.repository.NaverRepositoryDataSourceImpl

class RecentSearchViewModel @ViewModelInject constructor(
    private val naverRepositoryDataSource: NaverRepositoryDataSourceImpl
) : BaseViewModel() {
    private val _queryList = MutableLiveData<List<String>>()
    val queryList: LiveData<List<String>> = _queryList

    fun searchRecentQuery() {
        visible.value = false
        val list = naverRepositoryDataSource.getQueryList()
        _queryList.value = list
        visible.value = false
    }
}