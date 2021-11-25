package com.parveztest.rxjava.MVVM.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    lateinit var retrofit: Retrofit

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val retrofitInstance: Retrofit
        get() {
            if (!this::retrofit.isInitialized) {
                val okHttpClient = OkHttpClient()
                    .newBuilder()
                    .followRedirects(true)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }
}