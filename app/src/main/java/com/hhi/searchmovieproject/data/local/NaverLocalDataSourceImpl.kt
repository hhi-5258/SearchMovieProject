package com.hhi.searchmovieproject.data.local

import com.hhi.searchmovieproject.data.local.SharedPreferenceUtil
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.json.JSONArray
import javax.inject.Inject
import javax.inject.Singleton

class NaverLocalDataSourceImpl @Inject constructor(
    private val sharedPreferenceUtil: SharedPreferenceUtil
) : NaverLocalDataSource {
    override fun saveQuery(query: String) {
        val queryList = getQueryList().toMutableList()
        queryList.add(query)

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }

        sharedPreferenceUtil.setString(JSONArray(queryList).toString(), PREF_QUERY_LIST)

    }

    override fun getQueryList(): List<String> {
        val queryListJSONString = sharedPreferenceUtil.getString(PREF_QUERY_LIST)
        val queryList = mutableListOf<String>()

        queryListJSONString?.let {
            if (it.isNotEmpty()) {
                val jsonArray = JSONArray(it)
                for (i in 0 until jsonArray.length()) {
                    queryList.add(jsonArray.getString(i))
                }
            }
        }
        return queryList
    }

    companion object {
        private const val PREF_QUERY_LIST: String = "pref_query_list"
    }
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class NaverLocalDataModule {
    @Binds
    @Singleton
    abstract fun bindNaverLocalData(naverLocalDataSourceImpl: NaverLocalDataSourceImpl): NaverLocalDataSource
}