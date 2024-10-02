package com.akshays.newsinshortsnmb.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.akshays.newsinshortsnmb.data.entity.Article
import com.akshays.newsinshortsnmb.data.entity.NewsResponse
import com.akshays.newsinshortsnmb.ui.theme.Purple40

@Composable
fun Loader() {
    Column(modifier = Modifier.fillMaxSize()
    , horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center ) {
        CircularProgressIndicator(modifier = Modifier
            .size(50.dp)
            .padding(10.dp),
            color = Purple40
        )
    }

}

@Composable
fun NewsList(response:NewsResponse) {
    LazyColumn {
        response.articles?.let {

            items(items = it) {
                NewsItem(it)
            }
        }

    }
    
}

@Composable
fun NewsRowComponent(page: Int =0, article: Article) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(Color.White)) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            model = article.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.size(20.dp))
        HeadingTextComponent(textValue = article.title?:"")
        Spacer(modifier = Modifier.size(10.dp))
        NormalTextComponents(textValue = article.content?:"")
        Spacer(modifier = Modifier.size(10.dp))
        NormalTextComponents(textValue = article.description?:"")

    }
}

//@Preview
//@Composable
//fun preview() {
//    NewsRowComponent(article = Article(
//        title = "AA",
//
//    ))
//}

@Composable
fun NewsItem(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        NormalTextComponents(textValue = article.title ?: "No Title")
        NormalTextComponents(textValue = article.description ?: "No Description")
        NormalTextComponents(textValue = "By ${article.author ?: "Unknown"}")
    }
}

@Composable
fun NormalTextComponents(textValue:String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    )
}

@Composable
fun HeadingTextComponent(textValue:String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    )
}