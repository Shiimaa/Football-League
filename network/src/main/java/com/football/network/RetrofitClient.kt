package com.football.network

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.football-data.org/v4/"
    private lateinit var INSTANCE: Retrofit
    private val loggingInterceptor = HttpLoggingInterceptor()

    private val mutex = Object()

    private val httpClient = OkHttpClient.Builder()
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofitInstance(): Retrofit {
        synchronized(mutex) {
            if (!(::INSTANCE.isInitialized)) {
                Log.d(
                    RetrofitClient::class.java.simpleName,
                    "[getClient] attempt to init retrofit instance"
                )

                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                httpClient.addInterceptor(loggingInterceptor)
                httpClient.addInterceptor(AppInterceptor())

                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()

            }
        }
        return INSTANCE
    }
}