package com.football.network

import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.header("X-Auth-Token", "52e1308802de43b49bba5455508d29e3")

        val response = chain.proceed(builder.build())

        return response
    }
}