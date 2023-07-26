package com.example.quotesapp.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.adapters.QuoteAdapter
import com.example.quotesapp.models.Quote
import com.example.quotesapp.room.QuoteDatabase

class DisplayFavFragment : Fragment() {
    private lateinit var quotesList: ArrayList<Quote>
    private var TAG = "QUOTE FRAGMENT"
    private lateinit var quoteAdapter: QuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display_fav, container, false)
        quotesList = ArrayList()
        quoteAdapter = QuoteAdapter(quotesList){
            val action: NavDirections = DisplayFavFragmentDirections.actionDisplayFavFragmentToQuoteDetailBottomSheet2(it, butonEnabled = false)
            findNavController().navigate(action)
        }
        val recyclerView: RecyclerView = view.findViewById(R.id.favQuoteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = quoteAdapter
        val database = QuoteDatabase.getDatabaseInstance(this.requireContext())
        database.quoteDao().getQuotes().observe(viewLifecycleOwner) { quotes ->
            quoteAdapter.submitList(quotes)
        }

        return view
    }

}