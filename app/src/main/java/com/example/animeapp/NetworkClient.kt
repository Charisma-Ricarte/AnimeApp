package com.example.animeapp

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler

class NetworkClient {
    private val client = AsyncHttpClient()
    private val baseUrl = "https://api.jikan.moe/v4"

    // Fetch Anime List based on search query
    fun getAnimeList(query: String, callback: (List<Anime>) -> Unit) {
        val url = "$baseUrl/anime?q=$query"

        Log.d("NetworkClient", "Requesting URL: $url")  // Log the URL to check for correctness

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: okhttp3.Headers?, response: JSON?) {
                try {
                    val jsonObject = response?.jsonObject ?: return
                    val data = jsonObject.optJSONArray("data") ?: return

                    val animeList = mutableListOf<Anime>()
                    for (i in 0 until data.length()) {
                        val animeObject = data.getJSONObject(i)

                        val malId = animeObject.optInt("mal_id", 0)
                        val title = animeObject.optString("title", "Unknown Title")
                        val imageUrl = animeObject.optJSONObject("images")
                            ?.optJSONObject("jpg")
                            ?.optString("image_url", "")

                        val genres = animeObject.optJSONArray("genres")
                        val genreList = mutableListOf<String>()
                        if (genres != null) {
                            for (j in 0 until genres.length()) {
                                val genreObject = genres.getJSONObject(j)
                                genreList.add(genreObject.optString("name", "Unknown"))
                            }
                        }

                        val synopsis = animeObject.optString("synopsis", "No synopsis available.")
                        val rating = animeObject.optDouble("score", 0.0)

                        animeList.add(
                            Anime(
                                id = malId,
                                title = title,
                                imageUrl = imageUrl,
                                genres = genreList,
                                rating = rating
                            )
                        )
                    }

                    callback(animeList)

                } catch (e: Exception) {
                    Log.e("NetworkClient", "Error parsing response", e)
                }
            }

            override fun onFailure(statusCode: Int, headers: okhttp3.Headers?, response: String?, throwable: Throwable?) {
                Log.e("NetworkClient", "Request failed. Status code: $statusCode", throwable)
                Log.e("NetworkClient", "Response: $response")
            }
        })
    }

    // Fetch Genre List
    fun getGenreList(callback: (List<Genre>) -> Unit) {
        val url = "$baseUrl/genres/anime"
        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: okhttp3.Headers?, response: JSON?) {
                try {
                    val genreList = mutableListOf<Genre>()
                    val jsonObject = response?.jsonObject ?: return
                    val data = jsonObject.getJSONArray("data")

                    for (i in 0 until data.length()) {
                        val genreObject = data.getJSONObject(i)
                        genreList.add(
                            Genre(
                                id = genreObject.getInt("mal_id"),
                                name = genreObject.getString("name")
                            )
                        )
                    }

                    callback(genreList)

                } catch (e: Exception) {
                    Log.e("NetworkClient", "Error parsing genre list", e)
                }
            }

            override fun onFailure(statusCode: Int, headers: okhttp3.Headers?, response: String?, throwable: Throwable?) {
                Log.e("NetworkClient", "Request failed. Status code: $statusCode", throwable)
                if (statusCode == 429) {
                    Log.e("NetworkClient", "Rate limit reached. Retrying after delay...")
                    Handler(Looper.getMainLooper()).postDelayed({ getGenreList(callback) }, 2000) // Retry after 2 seconds
                }
            }
        })
    }
}
