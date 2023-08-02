package com.example.quotesapp.quotes



import android.util.Log
import com.example.quotesapp.models.Quote
import com.example.quotesapp.retrofit.RetrofitInstance
import com.example.quotesapp.room.QuoteDao
import com.example.quotesapp.room.QuoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

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
            Log.i(TAG, "Some exception occured")
        }

    }

    fun getFavoriteQuotes(): Flow<List<Quote>> {
        return quoteDao.getFavoriteQuotes()
    }
    
}