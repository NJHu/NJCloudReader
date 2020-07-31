package com.github.njhu.njcloudreader.fragment

import android.app.Service
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.njhu.njcloudreader.Base.BaseFragment
import com.github.njhu.njcloudreader.Bean.Article
import com.github.njhu.njcloudreader.R
import com.github.njhu.njcloudreader.Tool.ServiceCreator
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.home_content_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class HomeContent: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews(){
        home_banner.setRecyclerView(banner_recycler_view)
    }

    private fun loadData() {
        ServiceCreator.createAppService().getArticleList().enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                val list = response.body()
                if (list != null) {
                    for (article in list) {
                        Log.d("MianActivity", "title is ${article.title}")
                    }
                }
            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}