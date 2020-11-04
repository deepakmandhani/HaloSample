package com.example.haloapp.ui.main.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Deepak Mandhani on 2020-06-26.
 */

object ApiService {

    private var retrofit: Retrofit.Builder? = null
    private const val URL = "https://hn.algolia.com/api/v1/"

    private fun getRetrofitService(): Retrofit.Builder {
        return retrofit
            ?: Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient())
                .baseUrl(URL).also { retrofit = it }
    }

    fun getRetrofit() =
        getRetrofitService().build().create(NewsApi::class.java)
}