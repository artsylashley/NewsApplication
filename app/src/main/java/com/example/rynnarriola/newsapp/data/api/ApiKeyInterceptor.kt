    package com.example.rynnarriola.newsapp.data.api

import com.example.rynnarriola.newsapp.di.qualifiers.ApiKey
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(@ApiKey private val apiKey: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-Api-Key", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}