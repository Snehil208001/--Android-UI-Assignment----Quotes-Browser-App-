package com.example.quotesbrowserapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.quotesbrowserapp.data.QuoteRepository
import com.example.quotesbrowserapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition() // Wait for recycler view to load for transition

        val adapter = QuoteAdapter { quote, imageView ->
            val extras = FragmentNavigatorExtras(
                imageView to "quote_image_transition"
            )
            val action = HomeFragmentDirections.actionHomeToDetail(quote.id)
            findNavController().navigate(action, extras)
        }

        binding.recyclerViewHome.adapter = adapter
        adapter.submitList(QuoteRepository.getAllQuotes())

        // Start transition once view is drawn
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}