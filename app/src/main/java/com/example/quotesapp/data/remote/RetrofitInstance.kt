package com.example.quotesapp.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val api: QuotesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://type.fit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuotesApi::class.java)
    }
}
fun myHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
        .addInterceptor(AuthInterceptor())
    return builder.build()
}