package com.example.quotesbrowserapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quotesbrowserapp.data.QuoteRepository
import com.example.quotesbrowserapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadFavorites()
    }

    private fun loadFavorites() {
        val favorites = QuoteRepository.getFavorites(requireContext())

        if (favorites.isEmpty()) {
            binding.textEmptyState.visibility = View.VISIBLE
            binding.recyclerViewFavorites.visibility = View.GONE
        } else {
            binding.textEmptyState.visibility = View.GONE
            binding.recyclerViewFavorites.visibility = View.VISIBLE

            val adapter = QuoteAdapter { quote, imageView ->
                val action = FavoritesFragmentDirections.actionFavoritesToDetail(quote.id)
                findNavController().navigate(action)
            }
            binding.recyclerViewFavorites.adapter = adapter
            adapter.submitList(favorites)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}