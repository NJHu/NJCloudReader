package com.github.njhu.njcloudreader.Activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.github.njhu.njcloudreader.Base.BaseActivity
import com.github.njhu.njcloudreader.Base.BaseFragment
import com.github.njhu.njcloudreader.R
import com.github.njhu.njcloudreader.fragment.Discovery
import com.github.njhu.njcloudreader.fragment.Home
import com.github.njhu.njcloudreader.fragment.Music
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_header.*

class MainActivity : BaseActivity() {

    val homeFragment = Home()
    val musicFragment = Music()
    val discoveryFragment = Discovery()
    var currentFragment: Fragment = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addActions()
        replaceFragment(homeFragment)
    }
    private fun addActions() {
        val menu = findViewById<FrameLayout>(R.id.nav_menu_button)
        val music = findViewById<FrameLayout>(R.id.nav_music_button)
        val musicIcon = findViewById<FrameLayout>(R.id.nav_music_icon_button)
        val people = findViewById<FrameLayout>(R.id.nav_people_button)
        val search = findViewById<FrameLayout>(R.id.nav_search_button)

        val musicImageView = findViewById<ImageView>(R.id.nav_music_button_img)
        val musicIconImageView = findViewById<ImageView>(R.id.nav_music_icon_button_img)
        val peopleImageView = findViewById<ImageView>(R.id.nav_people_button_img)

        menu.setOnClickListener {
            draw_layout.openDrawer(GravityCompat.START)
        }
        music.setOnClickListener {
            musicImageView.setImageResource(R.drawable.titlebar_music_selected)
            musicIconImageView.setImageResource(R.drawable.titlebar_discover_normal)
            peopleImageView.setImageResource(R.drawable.titlebar_friends_normal)
            replaceFragment(homeFragment)
        }
        musicIcon.setOnClickListener {
            musicImageView.setImageResource(R.drawable.titlebar_music_normal)
            musicIconImageView.setImageResource(R.drawable.titlebar_discover_selected)
            peopleImageView.setImageResource(R.drawable.titlebar_friends_normal)
            replaceFragment(musicFragment)
        }
        people.setOnClickListener {
            musicImageView.setImageResource(R.drawable.titlebar_music_normal)
            musicIconImageView.setImageResource(R.drawable.titlebar_discover_normal)
            peopleImageView.setImageResource(R.drawable.titlebar_friends_selected)
            replaceFragment(discoveryFragment)
        }
        search.setOnClickListener {

        }
    }
    private fun replaceFragment(fragment: BaseFragment) {
        val fragmentManager = supportFragmentManager
        val transition = fragmentManager.beginTransaction()

        if (currentFragment != fragment) {
            transition.hide(currentFragment)
            currentFragment = fragment
            if (!fragment.isAdded) {
                transition.add(R.id.content_frame, fragment)
                transition.show(fragment).commit()
            } else {
                transition.show(fragment).commit()
            }
        }
    }
}
