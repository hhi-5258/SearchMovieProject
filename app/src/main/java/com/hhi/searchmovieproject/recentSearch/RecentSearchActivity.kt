package com.hhi.searchmovieproject.recentSearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.hhi.searchmovieproject.R
import com.hhi.searchmovieproject.base.BaseActivity
import com.hhi.searchmovieproject.databinding.ActivityRecentSearchBinding
import com.hhi.searchmovieproject.viewmodel.RecentSearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecentSearchActivity :
    BaseActivity<ActivityRecentSearchBinding>(R.layout.activity_recent_search) {
    private val vm: RecentSearchViewModel by viewModels()
    private val adapter = RecentSearchRecyclerAdapter(
        onClick = { query ->
            val intent = Intent()
            intent.putExtra("query", query)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = vm

        setUpUi()

        vm.searchRecentQuery()

    }

    private fun setUpUi() {
        binding.recentSearchRecyclerview.setHasFixedSize(false)
        binding.recentSearchRecyclerview.adapter = adapter
    }
}