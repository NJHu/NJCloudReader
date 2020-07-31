package com.github.njhu.njcloudreader.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.njhu.njcloudreader.Base.BaseFragment
import com.github.njhu.njcloudreader.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*

class Home: BaseFragment() {
    val homeContent = HomeContent()
    val musicFragment = Music()
    var currentFragment: Fragment = Fragment()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val home_fragment = inflater.inflate(R.layout.home_fragment, container, false)
        home_fragment.findViewById<TextView>(R.id.home_page).setOnClickListener {
            replaceFragment(homeContent)
        }
        home_fragment.findViewById<TextView>(R.id.live_page).setOnClickListener {
            replaceFragment(musicFragment)
        }
        return home_fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        replaceFragment(homeContent)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun replaceFragment(fragment: BaseFragment) {

        val fragmentManager = childFragmentManager
        val transition = fragmentManager.beginTransaction()

        if (currentFragment != fragment) {
            transition.hide(currentFragment)
            currentFragment = fragment
            if (!fragment.isAdded) {
                transition.add(R.id.content_page, fragment)
                transition.show(fragment).commit()
            } else {
                transition.show(fragment).commit()
            }
        }

    }
}