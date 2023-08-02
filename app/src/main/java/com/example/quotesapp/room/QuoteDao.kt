package com.example.quotesapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.quotesapp.models.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuote(quote : Quote)

    @Query("SELECT * FROM quotes")
    fun getQuotes() : Flow<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<Quote>)

    @Query("SELECT * FROM quotes WHERE isFav = 1")
    fun getFavoriteQuotes(): Flow<List<Quote>>

    @Update
    suspend fun updateQuote(quote: Quote)

}