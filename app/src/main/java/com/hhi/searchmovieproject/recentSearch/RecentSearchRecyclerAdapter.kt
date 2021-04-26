package com.hhi.searchmovieproject.recentSearch

import com.hhi.searchmovieproject.databinding.RecentSearchRecyclerItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecentSearchRecyclerAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<RecentSearchRecyclerAdapter.ViewHolder>() {
    private val queryList = mutableListOf<String>()

    fun setQueryList(list: List<String>) {
        with(this.queryList) {
            clear()
            addAll(list.asReversed())
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchRecyclerAdapter.ViewHolder {
        val binding = RecentSearchRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return queryList.size
    }

    override fun onBindViewHolder(holder: RecentSearchRecyclerAdapter.ViewHolder, position: Int) {
        val query = queryList[position]
        holder.bind(query)
    }

    inner class ViewHolder(
        private val binding: RecentSearchRecyclerItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(query: String) {
            binding.recentItemTextTitle.text = query
            binding.root.setOnClickListener { onClick(query) }
        }
    }
}