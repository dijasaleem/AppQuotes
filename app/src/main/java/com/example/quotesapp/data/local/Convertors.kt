package com.example.quotesapp.data.local

import androidx.room.TypeConverter

class Convertors {

    @TypeConverter
     fun fromListToString(value : List<String>) : String{
         return value.joinToString(",")
     }

    @TypeConverter
    fun fromStringToList(value: String) : List<String>{
        return value.split(",")
    }
}