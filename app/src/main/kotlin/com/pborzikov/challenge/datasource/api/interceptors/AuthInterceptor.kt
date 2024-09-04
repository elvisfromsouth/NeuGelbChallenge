package com.pborzikov.challenge.datasource.api.interceptors

import okhttp3.Interceptor

class AuthInterceptor(
    private val apiKey: String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()

        val httpUrl = request.url.newBuilder()
            .build()

        val newRequest = request.newBuilder()
            .header("Authorization", "Bearer $apiKey")
            .url(httpUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
