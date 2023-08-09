package com.example.quotesapp.ui.theme.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.quotesapp.R
import com.example.quotesapp.data.local.QuoteDatabase
import com.example.quotesapp.ui.theme.quotes.QuoteDetailBottomSheetArgs
import com.example.quotesapp.ui.theme.viewModels.DisplayDetailViewModel
import com.example.quotesapp.ui.theme.viewModels.DisplayQuoteViewModel
import com.example.quotesapp.util.QuoteViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class QuoteDetailBottomSheet : BottomSheetDialogFragment() {
    private val args : QuoteDetailBottomSheetArgs by navArgs()
    private lateinit var viewModel: DisplayDetailViewModel
    private lateinit var author : TextView
    private lateinit var button: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_quote_detail, container, false)
        author = view.findViewById(R.id.quote_author)
        author.text = "Author: " + args.quoteDetail.author
        button = view.findViewById(R.id.add_to_fav)
        viewModel = ViewModelProvider(this, QuoteViewModelFactory(this.requireContext()))[DisplayDetailViewModel::class.java]
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!args.butonEnabled)
            button.isEnabled = false
        else {
            button.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    val quote = args.quoteDetail
                    viewModel.addToFavorites(quote)
                }
                dismiss()
            }

        }
    }
}