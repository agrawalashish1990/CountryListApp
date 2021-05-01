package com.dev.ashish.countrylist.network

import com.dev.ashish.countrylist.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
// Created by Ashish on 01/05/21.
//

object ApiClient {

    lateinit var apiInterface: ApiInterface
    var lock = Any()

    fun getClient(): ApiInterface {
        if (!::apiInterface.isInitialized) {
            synchronized(lock) {
                if (!::apiInterface.isInitialized) {
                    initClient()
                }
            }

        }
        return apiInterface
    }

    private fun initClient() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpLogInterceptor = HttpLoggingInterceptor()
        httpLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(httpLogInterceptor)
        builder.addInterceptor(headersInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    private val headersInterceptor = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()
        )
    }
}