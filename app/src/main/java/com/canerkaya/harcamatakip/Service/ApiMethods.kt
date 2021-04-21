package com.canerkaya.harcamatakip.Service

import com.canerkaya.harcamatakip.Model.TrToUsdModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ApiMethods {

    @GET("?q=EUR_TRY&compact=ultra&apiKey=e95c24e1abf8f4b63baa")
    fun tryToUsd():Call<TrToUsdModel>
}