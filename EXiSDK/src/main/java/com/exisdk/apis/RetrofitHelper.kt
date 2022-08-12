
package com.exisdk.apis

import com.exisdk.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitHelper {

    fun getInstance(jwsKey: String): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(getHeader(jwsKey))
            .build()
    }

    fun getHeader(jwsKey:String): OkHttpClient? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor { chain ->
                var request: Request? = null
                val original: Request = chain.request()
                    // Request customization: add request headers
                    val requestBuilder: Request.Builder = original.newBuilder()
                        .addHeader("x-api-key", Constants.X_API_KEY)
                        .addHeader("jwsKey",jwsKey)
                    request = requestBuilder.build()

                chain.proceed(request)
            }
            .build()
    }

}
