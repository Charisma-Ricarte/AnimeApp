package com.example.animeapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    private lateinit var animeRecyclerView: RecyclerView
    private lateinit var animeAdapter: AnimeAdapter
    private val animeList = mutableListOf<Anime>()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        animeRecyclerView = findViewById(R.id.animeRecyclerView)
        animeAdapter = AnimeAdapter(animeList)
        animeRecyclerView.adapter = animeAdapter
        animeRecyclerView.layoutManager = LinearLayoutManager(this)

        // Search view
        searchView = findViewById(R.id.searchView)

        // Handle the search query
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    fetchAnimeList(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.isNotEmpty()) {
                    fetchAnimeList(newText)
                }
                return true
            }
        })
    }

    // Method to fetch anime based on the query
    private fun fetchAnimeList(query: String) {
        val networkClient = NetworkClient()
        networkClient.getAnimeList(query) { fetchedAnimeList ->

            // Filter the list to only include anime titles that contain the query (case insensitive)
            val filteredList = fetchedAnimeList.filter {
                it.title.contains(query, ignoreCase = true)
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No anime found", Toast.LENGTH_SHORT).show()
            } else {
                animeList.clear()
                animeList.addAll(filteredList)
                animeAdapter.notifyDataSetChanged()
            }
        }
    }
}
