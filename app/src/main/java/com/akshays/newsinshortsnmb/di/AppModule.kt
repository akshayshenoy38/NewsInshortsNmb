package com.akshays.newsinshortsnmb.di

import com.akshays.newsinshortsnmb.data.AppConstants
import com.akshays.newsinshortsnmb.data.api.ApiService
import com.akshays.newsinshortsnmb.data.datasource.NewsDataSource
import com.akshays.newsinshortsnmb.data.datasource.NewsDataSourceImpl
import com.akshays.newsinshortsnmb.ui.repositories.NewsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {

        val httpLoggingInterseptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterseptor)
            readTimeout(60, TimeUnit.SECONDS)
        }

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()



        return Retrofit.Builder()
            .baseUrl(AppConstants.APP_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun ApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesDataSource(apiService: ApiService): NewsDataSource {
        return NewsDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun providesNewsRepository( dataSource: NewsDataSource):NewsRepository {
        return NewsRepository(dataSource)
    }
}