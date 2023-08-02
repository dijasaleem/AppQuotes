package com.example.quotesapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "quotes")
data class Quote(

    val author: String,
    @PrimaryKey
    val text: String,
    @ColumnInfo(defaultValue = "0")
    var isFav: Boolean

):Parcelable