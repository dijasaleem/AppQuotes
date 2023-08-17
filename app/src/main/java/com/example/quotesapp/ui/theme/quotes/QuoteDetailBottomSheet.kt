package com.example.quotesapp.ui.theme.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.quotesapp.R
import com.example.quotesapp.ui.theme.viewModels.DisplayDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuoteDetailBottomSheet : BottomSheetDialogFragment() {
    private val args : QuoteDetailBottomSheetArgs by navArgs()
    private val model by viewModel<DisplayDetailViewModel>()
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
                    model.addToFavorites(quote)
                }
                dismiss()
            }

        }
    }
}