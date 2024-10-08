package com.akshays.newsinshortsnmb.data.api

import com.akshays.newsinshortsnmb.data.AppConstants
import com.akshays.newsinshortsnmb.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country:String,
        @Query("apiKey") apiKey:String = AppConstants.API_KEY
    ) : Response<NewsResponse>


}