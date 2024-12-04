package com.example.animeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager

class CategoryAdapter(private val categoryList: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.categoryName.text = getGenreName(category.genreId)

        // Set layout manager for anime RecyclerView (horizontal scroll)
        holder.animeRecyclerView.layoutManager = LinearLayoutManager(holder.animeRecyclerView.context, LinearLayoutManager.HORIZONTAL, false)

        // Set the adapter to show anime in the category
        val animeAdapter = AnimeAdapter(category.animeList)
        holder.animeRecyclerView.adapter = animeAdapter
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    private fun getGenreName(genreId: Int): String {
        return when (genreId) {
            2 -> "Adventure"
            10 -> "Fantasy"
            25 -> "Shoujo"
            27 -> "Shounen"
            else -> "Unknown"
        }
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        val animeRecyclerView: RecyclerView = itemView.findViewById(R.id.animeRecyclerView)
    }
}

