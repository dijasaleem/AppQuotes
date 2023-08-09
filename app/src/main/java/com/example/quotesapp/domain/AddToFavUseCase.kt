package com.example.quotesapp.domain

import com.example.quotesapp.data.QuoteRepository
import com.example.quotesapp.models.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AddToFavUseCase(
    private val quoteRepository: QuoteRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default)
{
    suspend operator fun invoke(quote: Quote) = withContext(defaultDispatcher) {
        quoteRepository.updateQuote(quote)
    }
}