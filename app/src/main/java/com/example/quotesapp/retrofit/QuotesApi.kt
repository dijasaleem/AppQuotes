package com.example.quotesapp.retrofit

import com.example.quotesapp.models.Quote
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {

    @GET("api/quotes")
    suspend fun getQuotes() : Response<List<Quote>>


}