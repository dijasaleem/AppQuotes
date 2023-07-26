package com.example.quotesapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "Quote")
data class Quote(

    val author: String,
    @PrimaryKey
    val text: String

):Parcelable