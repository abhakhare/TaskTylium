package com.example.tasktylium.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : PricesAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://md.lmaxglobal.io/fixprof/instruments/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PricesAPI::class.java)
    }
}