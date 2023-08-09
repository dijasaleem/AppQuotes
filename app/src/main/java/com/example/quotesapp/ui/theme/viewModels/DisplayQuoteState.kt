package com.example.quotesapp.ui.theme.viewModels

import com.example.quotesapp.models.Quote

sealed class DisplayQuoteState {
    object Loading : DisplayQuoteState()
    data class Success(val quotes: List<Quote>) : DisplayQuoteState()
    data class Error(val message: String) : DisplayQuoteState()
}
