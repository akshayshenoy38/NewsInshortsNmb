package com.akshays.newsinshortsnmb

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }
    
    companion object {
        private const val TAG = "NewsApplication"
    }
}

