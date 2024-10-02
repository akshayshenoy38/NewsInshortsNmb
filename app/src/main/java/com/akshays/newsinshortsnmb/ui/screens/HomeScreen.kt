package com.akshays.newsinshortsnmb.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akshays.newsinshortsnmb.ui.components.Loader
import com.akshays.newsinshortsnmb.ui.components.NewsRowComponent
import com.akshays.newsinshortsnmb.ui.viewmodels.NewsViewModel
import com.akshays.utilities.ResourceState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(newsViewModel: NewsViewModel = hiltViewModel()) {
    val newsRes by  newsViewModel.news.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        100
    }

    VerticalPager(state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp,

            ) { page:Int ->


            when(newsRes) {
                is ResourceState.Loading -> {
                    Log.d(TAG, "HomeScreen: Loading")
                    Loader()
                }

                is ResourceState.Success -> {

                    val response = (newsRes as ResourceState.Success).data
                   // NewsList(response = response)
                    response.articles?.let {
                        if (it.isNotEmpty()) {
                            NewsRowComponent(page, it.get(page))
                        }
                    }

                    Log.d(TAG, "HomeScreen: Success${response.status} ${response.totalResults}")
                }

                is ResourceState.Error -> {
                    val response = (newsRes as ResourceState.Error).error
                    Log.d(TAG, "HomeScreen: Error ${response}")

                }



        }
        
    }

}

private const val TAG = "HomeScreen"

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()

}