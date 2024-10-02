package com.akshays.newsinshortsnmb.data.datasource

import com.akshays.newsinshortsnmb.data.api.ApiService
import com.akshays.newsinshortsnmb.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(private val apiService: ApiService): NewsDataSource {


    override suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
        return apiService.getNewsHeadline(country)
    }
}