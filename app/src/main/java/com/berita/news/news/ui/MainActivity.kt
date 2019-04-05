package com.berita.news.news.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.berita.news.news.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_berita.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val intent = Intent(this@MainActivity, Berita::class.java)
            startActivity(intent)
        }

    }






}
