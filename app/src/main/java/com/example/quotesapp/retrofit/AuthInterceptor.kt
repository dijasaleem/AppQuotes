package com.example.quotesapp.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer 5EmB5rS7IwU0FijEIeHMPOVwgQPrZ18qPNT2CJrr")
        return chain.proceed(request.build())
    }

}