package com.hhi.searchmovieproject

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hhi.searchmovieproject.data.model.MovieData
import com.hhi.searchmovieproject.main.MainRecyclerAdapter
import com.hhi.searchmovieproject.recentSearch.RecentSearchRecyclerAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<Any>?) {
    items?.let {
        when (val adapter = adapter) {
            is MainRecyclerAdapter -> adapter.setMovieList(it as ArrayList<MovieData.MovieItem>)
            is RecentSearchRecyclerAdapter -> adapter.setQueryList(
                it as List<String>
            )
        }
    }
}