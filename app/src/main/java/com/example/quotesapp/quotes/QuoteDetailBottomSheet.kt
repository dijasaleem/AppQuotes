package com.example.quotesapp.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.quotesapp.R
import com.example.quotesapp.room.QuoteDatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class QuoteDetailBottomSheet : BottomSheetDialogFragment() {
    private val args : QuoteDetailBottomSheetArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_quote_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val author: TextView = view.findViewById(R.id.quote_author)
        author.text = "Author: " + args.quoteDetail.author
        val button: Button = view.findViewById(R.id.add_to_fav)
        if (!args.butonEnabled)
            button.isEnabled = false
        else {
            button.setOnClickListener {
                val database = QuoteDatabase.getDatabaseInstance(this.requireContext())
                viewLifecycleOwner.lifecycleScope.launch {
                    database.quoteDao().insertQuote(args.quoteDetail)
                }
                dismiss()
            }

        }
    }
}