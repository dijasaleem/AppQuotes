package com.example.quotesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.models.Quote

class QuoteAdapter(private val quotesList: List<Quote>,
                   private val clickListener: (Quote) -> Unit) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : QuoteAdapter.QuoteViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuoteViewHolder(v){
            clickListener(quotesList[it])
        }
    }
    override fun onBindViewHolder(holder: QuoteAdapter.QuoteViewHolder, position: Int) {
        holder.quoteText.text = quotesList[position].text
    }
    override fun getItemCount(): Int {
       return quotesList.size
    }
    inner class QuoteViewHolder(itemView: View, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        var quoteText: TextView
        init {
            quoteText = itemView.findViewById(R.id.quote_text)
            itemView.setOnClickListener{
                clickAtPosition(adapterPosition)
            }
        }
    }

}