package com.example.quotesapp.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.data.QuoteRepository
import com.example.quotesapp.data.local.QuoteDatabase
import com.example.quotesapp.domain.AddToFavUseCase
import com.example.quotesapp.domain.GetFavoriteQuotesUseCase
import com.example.quotesapp.domain.GetQuotesUseCase
import com.example.quotesapp.ui.theme.quotes.QuoteDetailBottomSheet
import com.example.quotesapp.ui.theme.viewModels.DisplayDetailViewModel
import com.example.quotesapp.ui.theme.viewModels.DisplayFavViewModel
import com.example.quotesapp.ui.theme.viewModels.DisplayQuoteViewModel

class QuoteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    private lateinit var getQuotesUseCase: GetQuotesUseCase
    private lateinit var getFavoriteQuotesUseCase: GetFavoriteQuotesUseCase
    private lateinit var addToFavUseCase:  AddToFavUseCase
    private val quoteRepository : QuoteRepository = QuoteRepository(QuoteDatabase.getDatabaseInstance(context).quoteDao())
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayQuoteViewModel::class.java)) {
            getQuotesUseCase = GetQuotesUseCase(quoteRepository)
            return DisplayQuoteViewModel(getQuotesUseCase) as T
        }
        else if(modelClass.isAssignableFrom(DisplayFavViewModel::class.java)){
            getFavoriteQuotesUseCase = GetFavoriteQuotesUseCase(quoteRepository)
            return DisplayFavViewModel(getFavoriteQuotesUseCase) as T
        }
        else if(modelClass.isAssignableFrom(DisplayDetailViewModel::class.java)){
            addToFavUseCase = AddToFavUseCase(quoteRepository)
            return DisplayDetailViewModel(addToFavUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
