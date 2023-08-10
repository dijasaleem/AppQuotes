package com.example.quotesapp.ui.theme.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.ui.theme.adapters.QuoteAdapter
import com.example.quotesapp.models.Quote
import com.example.quotesapp.ui.theme.viewModels.DisplayFavViewModel
import com.example.quotesapp.ui.theme.viewModels.DisplayQuoteState
import com.example.quotesapp.util.QuoteViewModelFactory
import kotlinx.coroutines.launch

class DisplayFavFragment : Fragment() {
    private var quotesList: ArrayList<Quote> = ArrayList()
    private var TAG = "QUOTE FRAGMENT"
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var viewModel : DisplayFavViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display_fav, container, false)
        viewModel = ViewModelProvider(this, QuoteViewModelFactory(this.requireContext()))[DisplayFavViewModel::class.java]
        quoteAdapter = QuoteAdapter(quotesList){
            val action: NavDirections =
                DisplayFavFragmentDirections.actionDisplayFavFragmentToQuoteDetailBottomSheet2(
                    it,
                    butonEnabled = false
                )
            findNavController().navigate(action)
        }
        val recyclerView: RecyclerView = view.findViewById(R.id.favQuoteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = quoteAdapter
        viewModel.fetchQuotes()
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                viewModel.viewState.collect { state ->
                    when (state) {
                        is DisplayQuoteState.Loading -> {
                            // Handle loading state
                        }
                        is DisplayQuoteState.Success -> {
                            quoteAdapter.submitList(state.quotes)
                        }
                        is DisplayQuoteState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        return view
    }

}