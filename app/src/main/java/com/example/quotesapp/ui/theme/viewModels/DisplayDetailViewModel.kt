package com.example.quotesapp.ui.theme.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domain.AddToFavUseCase
import com.example.quotesapp.models.Quote
import kotlinx.coroutines.launch

class DisplayDetailViewModel(private val addToFavUseCase: AddToFavUseCase) : ViewModel(){

    fun addToFavorites(quote: Quote) {
        viewModelScope.launch {
            quote.isFav = true
            addToFavUseCase.invoke(quote)
        }
    }
}