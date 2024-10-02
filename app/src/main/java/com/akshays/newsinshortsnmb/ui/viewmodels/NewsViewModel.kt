package com.akshays.newsinshortsnmb.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshays.newsinshortsnmb.data.AppConstants
import com.akshays.newsinshortsnmb.data.entity.NewsResponse
import com.akshays.newsinshortsnmb.ui.repositories.NewsRepository
import com.akshays.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _news:MutableStateFlow<ResourceState<NewsResponse>> = MutableStateFlow(ResourceState.Loading())
    val news : StateFlow<ResourceState<NewsResponse>> = _news
    init {
        Log.d(TAG, "init: called")
        getNews(AppConstants.COUNTRY)
    }

    fun getNews(country:String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getHeadline(country)
                .collectLatest { newsResponse ->
                    _news.value = newsResponse

                }
        }
    }
}

private const val TAG = "NewsViewModel"