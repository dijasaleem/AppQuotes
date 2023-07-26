package com.example.quotesapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quotesapp.models.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuote(quote : Quote)

    @Query("SELECT * FROM Quote")
    fun getQuotes() : LiveData<List<Quote>>

}