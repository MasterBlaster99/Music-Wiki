package com.example.musicwiki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicwiki.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, HomeFragment())
//            .commit()
    }
}