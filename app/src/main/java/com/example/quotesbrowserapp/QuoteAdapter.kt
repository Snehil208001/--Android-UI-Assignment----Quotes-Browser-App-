package com.example.quotesbrowserapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.quotesbrowserapp.data.Quote
import com.example.quotesbrowserapp.databinding.ItemQuoteBinding

class QuoteAdapter(
    private val onClick: (Quote, android.widget.ImageView) -> Unit
) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    private var quotes = listOf<Quote>()

    fun submitList(newQuotes: List<Quote>) {
        quotes = newQuotes
        notifyDataSetChanged()
    }

    inner class QuoteViewHolder(val binding: ItemQuoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        with(holder.binding) {
            textQuote.text = quote.text
            textAuthor.text = "- ${quote.author}"

            // Shared Element Transition Name unique per item
            imageQuote.transitionName = "quote_image_${quote.id}"

            imageQuote.load(quote.imageUrl) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
            }

            root.setOnClickListener {
                onClick(quote, imageQuote)
            }
        }
    }

    override fun getItemCount(): Int = quotes.size
}