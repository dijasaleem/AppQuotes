package com.example.quotesapp.ui.theme.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.quotesapp.models.Quote

class QuoteDiffCallback(private val oldList: List<Quote>, private val newList: List<Quote>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].text == newList[newItemPosition].text
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}
