package com.akshays.newsinshortsnmb.ui.repositories

import com.akshays.newsinshortsnmb.data.datasource.NewsDataSource
import com.akshays.newsinshortsnmb.data.entity.NewsResponse
import com.akshays.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

//    suspend fun getHeadline(country:String) : Response<NewsResponse> {
//        return newsDataSource.getNewsHeadline(country)
//    }

    suspend fun getHeadline(country:String) : Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = newsDataSource.getNewsHeadline(country)
            if (response.isSuccessful && response.body()!= null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("error fetching news Data"))
            }

        }.catch {
            emit(ResourceState.Error(it?.localizedMessage?:"Something Went Worng"))
        }
    }
}