package com.canerkaya.harcamatakip.Service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        val BASE_URL = "https://free.currconv.com/api/v7/convert/"
        fun getClient():Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}