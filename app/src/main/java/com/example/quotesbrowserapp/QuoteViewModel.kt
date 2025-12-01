package com.example.quotesbrowserapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quotesbrowserapp.data.Quote
import com.example.quotesbrowserapp.data.QuoteRepository

class QuoteViewModel(application: Application) : AndroidViewModel(application) {

    // Backing properties
    private val _quotes = MutableLiveData<List<Quote>>()
    val quotes: LiveData<List<Quote>> get() = _quotes

    private val _favorites = MutableLiveData<List<Quote>>()
    val favorites: LiveData<List<Quote>> get() = _favorites

    private val context = application.applicationContext

    init {
        loadQuotes()
        refreshFavorites()
    }

    private fun loadQuotes() {
        // In a real app, this might be a suspend function call
        _quotes.value = QuoteRepository.getAllQuotes()
    }

    // Refresh favorites from Repository (SharedPreferences)
    fun refreshFavorites() {
        _favorites.value = QuoteRepository.getFavorites(context)
    }

    fun getQuoteById(id: String): Quote? {
        return QuoteRepository.getQuoteById(id)
    }

    fun isFavorite(id: String): Boolean {
        return QuoteRepository.isFavorite(context, id)
    }

    fun toggleFavorite(id: String) {
        QuoteRepository.toggleFavorite(context, id)
        // Refresh the LiveData so observers (like FavoritesFragment) update automatically
        refreshFavorites()
    }
}