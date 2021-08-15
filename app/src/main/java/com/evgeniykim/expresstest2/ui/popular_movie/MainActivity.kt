package com.evgeniykim.expresstest2.ui.popular_movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.evgeniykim.expresstest2.R
import com.evgeniykim.expresstest2.ui.toprated.TopRatedFragment
import com.evgeniykim.expresstest2.ui.upcoming.UpcomingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag_container, PopularFragment())
                .commitAllowingStateLoss()
        }

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        val navController = findNavController(R.id.nav_fragment)
//        bottomNavigationView.setupWithNavController(navController)

        bottom_navigation.menu.getItem(0).isCheckable = true
        setFragment(PopularFragment())

        bottom_navigation.setOnNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.popularMovies -> {
                    Log.i("test", "popular")
                    setFragment(PopularFragment())
                    true
                }
                R.id.latestMovies -> {
                    Log.i("test", "latest")
                    setFragment(TopRatedFragment())
                    true
                }
                R.id.upcomingMovies -> {
                    Log.i("test", "upcoming")
                    setFragment(UpcomingFragment())
                    true
                }
                else -> false
            }

        }



    }

    fun setFragment(fragment: Fragment){
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.frag_container, fragment)
        frag.commit()
    }

}