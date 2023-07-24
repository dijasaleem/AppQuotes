package com.example.quotesapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quotesapp.models.Quote

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase : RoomDatabase(){

    abstract fun quoteDao() : QuoteDao

    companion object{

        private var INSTANCE: QuoteDatabase? = null
        fun getDatabaseInstance(context: Context) : QuoteDatabase{
            if(INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                QuoteDatabase::class.java,
                "quoteDB").build()
            }
            return INSTANCE!!
        }
    }

}