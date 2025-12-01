package com.example.quotesbrowserapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels // Import this
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.quotesbrowserapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Use activityViewModels to share the instance if needed, or viewModels() for local scope
    private val viewModel: QuoteViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()

        val adapter = QuoteAdapter { quote, imageView ->
            val extras = FragmentNavigatorExtras(
                imageView to "quote_image_transition"
            )
            val action = HomeFragmentDirections.actionHomeToDetail(quote.id)
            findNavController().navigate(action, extras)
        }

        binding.recyclerViewHome.adapter = adapter

        // OBSERVE LiveData instead of direct Repo call
        viewModel.quotes.observe(viewLifecycleOwner) { quoteList ->
            adapter.submitList(quoteList)
        }

        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}