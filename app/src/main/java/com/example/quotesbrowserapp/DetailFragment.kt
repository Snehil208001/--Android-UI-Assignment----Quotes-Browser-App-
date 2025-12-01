package com.example.quotesbrowserapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.load
import com.example.quotesbrowserapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    // Share ViewModel to update the same state observed by FavoritesFragment
    private val viewModel: QuoteViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quoteId = args.quoteId
        // Fetch via ViewModel
        val quote = viewModel.getQuoteById(quoteId) ?: return

        with(binding) {
            imageDetail.transitionName = "quote_image_transition"
            imageDetail.load(quote.imageUrl)
            textDetailQuote.text = "\"" + quote.text + "\""
            textDetailAuthor.text = "- ${quote.author}"

            updateFavoriteIcon(quote.id)

            fabFavorite.setOnClickListener {
                // Toggle via ViewModel
                viewModel.toggleFavorite(quote.id)
                updateFavoriteIcon(quote.id)
            }
        }
    }

    private fun updateFavoriteIcon(id: String) {
        // Check via ViewModel
        val isFav = viewModel.isFavorite(id)
        val iconRes = if (isFav) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
        binding.fabFavorite.setImageResource(iconRes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}