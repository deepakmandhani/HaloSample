package com.example.haloapp.ui.main.service

import NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Deepak Mandhani on 2020-06-26.
 */
interface NewsApi {

    @GET("search")
    fun fetchNews(@Query("query") search: String, @Query("page") page: Int): Call<NewsResponse>
}