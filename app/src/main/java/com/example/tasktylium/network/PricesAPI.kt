package com.example.tasktylium.network

import com.example.tasktylium.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface PricesAPI {
    @GET("prices")
    fun getAllPrices(): Call<Data>
}