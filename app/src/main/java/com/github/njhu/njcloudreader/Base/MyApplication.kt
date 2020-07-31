package com.github.njhu.njcloudreader.Base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

final class MyApplication: Application() {
    companion object {
        // @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}