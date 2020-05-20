package com.sofascorezadatak.sofascorezadatak

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sofascorezadatak.sofascorezadatak.model.Repository

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        val repository = intent.getSerializableExtra("repository") as Repository
        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager, repository)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val tabs = findViewById<TabLayout>(R.id.tabs)

        viewPager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

    companion object {
        @JvmStatic
        fun start(context: Context, repository: Repository) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("repository", repository)
            context.startActivity(intent)
        }
    }
}