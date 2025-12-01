package com.example.quotesbrowserapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object QuoteRepository {

    // The JSON provided in the prompt (Picsum data)
    private const val RAW_JSON = """
    [{"id":"0","author":"Alejandro Escamilla","width":5000,"height":3333,"url":"https://unsplash.com/photos/yC-Yzbqy7PY","download_url":"https://picsum.photos/id/0/5000/3333"},{"id":"1","author":"Alejandro Escamilla","width":5000,"height":3333,"url":"https://unsplash.com/photos/LNRyGwIJr5c","download_url":"https://picsum.photos/id/1/5000/3333"},{"id":"2","author":"Alejandro Escamilla","width":5000,"height":3333,"url":"https://unsplash.com/photos/N7XodRrbzS0","download_url":"https://picsum.photos/id/2/5000/3333"},{"id":"3","author":"Alejandro Escamilla","width":5000,"height":3333,"url":"https://unsplash.com/photos/Dl6jeyfihLk","download_url":"https://picsum.photos/id/3/5000/3333"},{"id":"4","author":"Alejandro Escamilla","width":5000,"height":3333,"url":"https://unsplash.com/photos/y83Je1OC6Wc","download_url":"https://picsum.photos/id/4/5000/3333"},{"id":"5","author":"Alejandro Escamilla","width":5000,"height":3334,"url":"https://unsplash.com/photos/LF8gK8-HGSg","download_url":"https://picsum.photos/id/5/5000/3334"},{"id":"6","author":"Alejandro Escamilla","width":5000,"height":3333,"url":"https://unsplash.com/photos/tAKXap853rY","download_url":"https://picsum.photos/id/6/5000/3333"},{"id":"7","author":"Alejandro Escamilla","width":4728,"height":3168,"url":"https://unsplash.com/photos/BbQLHCpVUqA","download_url":"https://picsum.photos/id/7/4728/3168"},{"id":"8","author":"Alejandro Escamilla","width":5000,"height":3333,"url":"https://unsplash.com/photos/xII7efH1G6o","download_url":"https://picsum.photos/id/8/5000/3333"},{"id":"9","author":"Alejandro Escamilla","width":5000,"height":3269,"url":"https://unsplash.com/photos/ABDTiLqDhJA","download_url":"https://picsum.photos/id/9/5000/3269"}]
    """

    // Hardcoded text to pair with the images
    private val QUOTE_TEXTS = listOf(
        "The only limit to our realization of tomorrow is our doubts of today.",
        "Do what you can, with what you have, where you are.",
        "Act as if what you do makes a difference. It does.",
        "Success is not final, failure is not fatal: it is the courage to continue that counts.",
        "Believe you can and you're halfway there.",
        "Happiness depends upon ourselves.",
        "It always seems impossible until it's done.",
        "Keep your face always toward the sunshine—and shadows will fall behind you.",
        "The best way to predict the future is to create it.",
        "You are never too old to set another goal or to dream a new dream."
    )

    // FIXED: Renamed property from 'allQuotes' to '_allQuotes' to avoid clash with getAllQuotes() function
    private val _allQuotes: List<Quote> by lazy {
        val listType = object : TypeToken<List<Map<String, Any>>>() {}.type
        val rawData: List<Map<String, Any>> = Gson().fromJson(RAW_JSON, listType)

        rawData.mapIndexed { index, map ->
            Quote(
                id = map["id"] as String,
                author = map["author"] as String,
                imageUrl = map["download_url"] as String,
                text = QUOTE_TEXTS.getOrElse(index) { "Inspirational Quote Placeholder" }
            )
        }
    }

    private const val PREFS_NAME = "favorite_prefs"
    private const val KEY_FAVORITES = "favorite_ids"

    // References updated to use the renamed property
    fun getAllQuotes(): List<Quote> = _allQuotes

    fun getQuoteById(id: String): Quote? = _allQuotes.find { it.id == id }

    fun getFavorites(context: Context): List<Quote> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favoriteIds = prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
        return _allQuotes.filter { favoriteIds.contains(it.id) }
    }

    fun isFavorite(context: Context, id: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favoriteIds = prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
        return favoriteIds.contains(id)
    }

    fun toggleFavorite(context: Context, id: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favoriteIds = prefs.getStringSet(KEY_FAVORITES, mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        val isFav = if (favoriteIds.contains(id)) {
            favoriteIds.remove(id)
            false
        } else {
            favoriteIds.add(id)
            true
        }

        prefs.edit().putStringSet(KEY_FAVORITES, favoriteIds).apply()
        return isFav
    }
}