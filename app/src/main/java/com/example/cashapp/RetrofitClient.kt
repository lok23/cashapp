package com.example.cashapp

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private const val BASE_URL_CASHAPP = "https://storage.googleapis.com"

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val cashAppAPIService: CashAppAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_CASHAPP)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(CashAppAPIService::class.java)
    }
}