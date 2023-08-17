package com.example.quotesapp.ui.theme.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.ui.theme.adapters.QuoteAdapter
import com.example.quotesapp.ui.theme.viewModels.DisplayQuoteState
import com.example.quotesapp.ui.theme.viewModels.DisplayQuoteViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DisplayQuoteFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var listAdapter : QuoteAdapter
    private val model by viewModel<DisplayQuoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display_quote, container, false)
        recyclerView = view.findViewById(R.id.quoteRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        listAdapter = QuoteAdapter(ArrayList()){
            val action: NavDirections =
                DisplayQuoteFragmentDirections.actionDisplayQuoteFragmentToQuoteDetailBottomSheet(
                    it,
                    true
                )
            findNavController().navigate(action)
        }
        recyclerView.adapter = listAdapter
         return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.fetchQuotes()
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                model.viewState.collect { state ->
                    when (state) {
                        is DisplayQuoteState.Loading -> {
                            // Handle loading state
                        }
                        is DisplayQuoteState.Success -> {
                            listAdapter.submitList(state.quotes)
                        }
                        is DisplayQuoteState.Error -> {
                          Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

}
