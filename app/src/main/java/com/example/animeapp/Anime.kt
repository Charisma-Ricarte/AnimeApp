package com.example.animeapp

data class Anime(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val genres: List<String>,
    val rating: Double // Add rating field
)

