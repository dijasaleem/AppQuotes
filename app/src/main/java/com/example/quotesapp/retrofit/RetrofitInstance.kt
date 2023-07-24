package com.example.quotesapp.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var gson = GsonBuilder()
        .setLenient()
        .create()
    val api: QuotesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://type.fit/")
            .addConverterFactory(GsonConverterFactory.create())
            //.client(myHttpClient())
            .build()
            .create(QuotesApi::class.java)
    }
}
fun myHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
        .addInterceptor(AuthInterceptor())
    return builder.build()
}