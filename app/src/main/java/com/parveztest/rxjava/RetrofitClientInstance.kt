package com.parveztest.rxjava

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    lateinit var retrofit: Retrofit

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

//    private var token = ""

    val retrofitInstance: Retrofit
        get() {
            if (!this::retrofit.isInitialized) {
//                val headersInterceptor = Interceptor { chain ->
//                    val requestBuilder = chain.request().newBuilder()
//                    requestBuilder.header("Authorization", "Bearer $token")
//                    chain.proceed(requestBuilder.build())
//                }
                val okHttpClient = OkHttpClient()
                    .newBuilder()
                    .followRedirects(true)
//                    .addInterceptor(headersInterceptor)
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

//    fun setToken(token: String) {
//        RetrofitClientInstance.token = token
//    }
}