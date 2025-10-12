package com.erickvazquezs.practicamodulo7.data.remote

import com.erickvazquezs.practicamodulo7.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}