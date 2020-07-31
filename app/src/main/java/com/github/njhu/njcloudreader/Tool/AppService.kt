package com.github.njhu.njcloudreader.Tool


import com.github.njhu.njcloudreader.Bean.Banner
import com.github.njhu.njcloudreader.Bean.BannerData
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import com.github.njhu.njcloudreader.Bean.Article as Article

interface AppService {
    @GET("files/articleList.json")
    fun getArticleList(): Call<List<Article>>
    
    @GET("banner/json")
    fun getBannerList(): Call<BannerData>
}