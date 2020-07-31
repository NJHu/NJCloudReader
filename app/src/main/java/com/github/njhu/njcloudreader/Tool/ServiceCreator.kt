package com.github.njhu.njcloudreader.Tool

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://njhu.github.io/"
    private const val API_WAN_ANDROID = "https://www.wanandroid.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitWan = Retrofit.Builder()
        .baseUrl(API_WAN_ANDROID)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>): T {
        return  retrofit.create(serviceClass)
    }
    fun createAppService(): AppService {
        return create(AppService::class.java)
    }
    fun <T> createWan(serviceClass: Class<T>): T {
        return  retrofitWan.create(serviceClass)
    }
    fun createAppWanService(): AppService {
        return createWan(AppService::class.java)
    }
}