package com.example.quotesapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
//@Entity(tableName = "Quote")
data class Quote(
    //@PrimaryKey(autoGenerate = true)
    val id : Long,
    val author: String,
    //val id: String,
    //val length: String?,
    val text: String,
    //val tags: List<Any>?
):Parcelable