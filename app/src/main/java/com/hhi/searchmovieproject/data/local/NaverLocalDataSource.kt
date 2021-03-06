package com.hhi.searchmovieproject.data.local

interface NaverLocalDataSource {
    fun saveQuery(query: String)

    fun getQueryList(): List<String>
}