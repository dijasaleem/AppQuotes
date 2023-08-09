package com.example.quotesapp.ui.theme.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domain.GetQuotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.IOException

class DisplayQuoteViewModel(private val getQuotesUseCase: GetQuotesUseCase) : ViewModel() {
    private val _viewState = MutableStateFlow<DisplayQuoteState>(DisplayQuoteState.Loading)
    val viewState: StateFlow<DisplayQuoteState> = _viewState.asStateFlow()
    fun fetchQuotes() {
       viewModelScope.launch {
            try {
                val quotesItemFlow = getQuotesUseCase.invoke()
                quotesItemFlow.collectLatest {
                    _viewState.value = DisplayQuoteState.Success(it)
                }
            } catch (ex: Exception ) {
                _viewState.value = DisplayQuoteState.Error("Unable to load data")
                }
            }
        }
}