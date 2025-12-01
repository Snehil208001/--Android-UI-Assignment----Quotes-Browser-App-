package com.example.quotesbrowserapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quotesbrowserapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    // Use activityViewModels to get the updated list from DetailFragment actions
    private val viewModel: QuoteViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = QuoteAdapter { quote, _ ->
            val action = FavoritesFragmentDirections.actionFavoritesToDetail(quote.id)
            findNavController().navigate(action)
        }
        binding.recyclerViewFavorites.adapter = adapter

        // Observe the favorites list
        viewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            if (favorites.isEmpty()) {
                binding.textEmptyState.visibility = View.VISIBLE
                binding.recyclerViewFavorites.visibility = View.GONE
            } else {
                binding.textEmptyState.visibility = View.GONE
                binding.recyclerViewFavorites.visibility = View.VISIBLE
                adapter.submitList(favorites)
            }
        }

        // Ensure data is fresh when view is created
        viewModel.refreshFavorites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}