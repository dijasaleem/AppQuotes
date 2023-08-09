package com.example.quotesapp.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.adapters.QuoteAdapter
import com.example.quotesapp.room.QuoteDatabase
import kotlinx.coroutines.launch

class DisplayQuoteFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var listAdapter : QuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display_quote, container, false)
        recyclerView = view.findViewById(R.id.quoteRecyclerView)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        listAdapter = QuoteAdapter(ArrayList()){
            val action: NavDirections = DisplayQuoteFragmentDirections.actionDisplayQuoteFragmentToQuoteDetailBottomSheet(it, true)
            findNavController().navigate(action)
        }
        recyclerView.adapter = listAdapter
         return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = QuoteDatabase.getDatabaseInstance(this.requireContext())
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED)
            {
               val quoteRepository = QuoteRepository(database.quoteDao())
                quoteRepository.getQuotes().collect{
                     listAdapter.submitList(it)
                 }
            }
        }
    }

}
