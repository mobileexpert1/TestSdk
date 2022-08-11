package com.exisdk.apis

import com.exisdk.models.QuoteList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    interface ApiInterface {
        @GET("/quotes")
        fun getQuotes(): Response<QuoteList>
        companion object {
            var BASE_URL = "https://quotable.io/"
            fun create(): ApiInterface {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                return retrofit.create(ApiInterface::class.java)
            }
        }
    }
}
