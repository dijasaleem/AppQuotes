package com.example.quotesapp.quotes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.adapters.QuoteAdapter
import com.example.quotesapp.models.Quote
import com.example.quotesapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DisplayQuoteFragment : Fragment(R.layout.fragment_display_quote) {
    private lateinit var quotesList: ArrayList<Quote>
    private var TAG = "QUOTE FRAGMENT"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //quotesList = ArrayList<Quote>()
        //quotesList.add(Quote(text = "hello", author = "khadija"))
       // quotesList.add(/* element = */ Quote(id = "012", quote = "hello", author = "khadija", length = "23", tags = null))

        viewLifecycleOwner.lifecycleScope.launch {
            val response = try {
                RetrofitInstance.api.getQuotes()
            } catch(e: IOException) {
                Log.i(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.i(TAG, "HttpException, unexpected response")
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                val recyclerView : RecyclerView = view.findViewById(R.id.quoteRecyclerView)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = QuoteAdapter(response.body()!!){
                        val action: NavDirections = DisplayQuoteFragmentDirections.actionDisplayQuoteFragmentToQuoteDetailBottomSheet(it)
                        findNavController().navigate(action)
                    }
                }
                return@launch
            } else {
                Log.i(TAG, "Response not successful")
            }
        }



    }

}
