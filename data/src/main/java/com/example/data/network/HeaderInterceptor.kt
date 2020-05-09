package com.example.data.network

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()

        requestBuilder.header("Authorization", "Bearer ".plus(BuildConfig.TOKEN))

        originalRequest = requestBuilder.build()

        return chain.proceed(originalRequest)
    }
}
