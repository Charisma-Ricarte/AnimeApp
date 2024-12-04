package com.example.animeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

class CatalogueActivity : AppCompatActivity() {

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private val categoryList = mutableListOf<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogue)

        // Initialize RecyclerView for categories
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView)
        categoryAdapter = CategoryAdapter(categoryList)
        categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        categoryRecyclerView.adapter = categoryAdapter

        // Fetch genres and anime list for each genre
        val networkClient = NetworkClient()
        val genres = listOf(2, 10, 25, 27) // Adventure, Fantasy, Shoujo, Shounen

        for (genreId in genres) {
            networkClient.getAnimeListByGenre(genreId) { animeList ->
                // Ensure anime list is retrieved for the genre
                categoryList.add(Category(genreId, animeList))

                // Notify adapter that the data has changed
                categoryAdapter.notifyDataSetChanged()
            }
        }

        // Button to go back to the search page
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish() // This will close the catalogue activity and go back to the previous one
        }
    }
}


