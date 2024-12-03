package com.example.animeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AnimeAdapter(private val animeList: List<Anime>) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    // ViewHolder class to hold references to item views
    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animeImage: ImageView = itemView.findViewById(R.id.animeCover)
        val animeTitle: TextView = itemView.findViewById(R.id.animeTitle)
        val animeGenres: TextView = itemView.findViewById(R.id.animeGenres)
        val animeRating: TextView = itemView.findViewById(R.id.animeRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]

        // Set the data to the views in the item layout
        Glide.with(holder.itemView.context)
            .load(anime.imageUrl)
            .into(holder.animeImage) // Set image

        holder.animeTitle.text = anime.title // Set title
        holder.animeGenres.text = "Genres: ${anime.genres.joinToString(", ")}" // Set genres
        holder.animeRating.text = "Rating: ${anime.rating}" // Set rating
    }

    override fun getItemCount(): Int = animeList.size
}

