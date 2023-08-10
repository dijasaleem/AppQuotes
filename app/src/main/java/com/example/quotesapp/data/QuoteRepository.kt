package com.example.quotesapp.data



import android.util.Log
import com.example.quotesapp.data.local.QuoteDao
import com.example.quotesapp.data.remote.RetrofitInstance
import com.example.quotesapp.models.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class QuoteRepository ( private val quoteDao : QuoteDao){
    private val TAG = "Quote_Repository"
    fun getQuotes(): Flow<List<Quote>> = flow {
        val cachedQuotes = quoteDao.getQuotes().first()
        if (cachedQuotes.isNotEmpty()) {
            emit(cachedQuotes)
        }

        try {
            val quotesResponseFromApi = RetrofitInstance.api.getQuotes()
            if(quotesResponseFromApi.isSuccessful && quotesResponseFromApi.body() != null)
            {
                quoteDao.insertAll(quotesResponseFromApi.body()!!)
                emit(quotesResponseFromApi.body()!!)
            }
        }
        catch(e : Exception){
            Log.i(TAG, "Some exception occurred")
        }

    }

    suspend fun updateQuote(quote: Quote){
        quoteDao.updateQuote(quote)
    }
    fun getFavoriteQuotes(): Flow<List<Quote>> {
        return quoteDao
            .getFavoriteQuotes()
    }
    
}