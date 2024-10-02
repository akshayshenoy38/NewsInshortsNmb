package com.akshays.newsinshortsnmb.data.datasource


import com.akshays.newsinshortsnmb.data.entity.NewsResponse
import retrofit2.Response


interface NewsDataSource {

    suspend fun getNewsHeadline(
        country:String
    ) : Response<NewsResponse>
}