package com.github.njhu.njcloudreader.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.njhu.njcloudreader.Base.BaseFragment
import com.github.njhu.njcloudreader.R
import kotlinx.android.synthetic.main.activity_main.*

class Discovery: BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.discovery_fragment, container, false)
    }
}