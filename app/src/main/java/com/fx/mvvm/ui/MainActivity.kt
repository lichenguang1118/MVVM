package com.fx.mvvm.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fx.mvvm.R
import com.fx.mvvm.ui.test.TestActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.textView5).setOnClickListener {
            startActivity(Intent(this@MainActivity, TestActivity::class.java))
        }
    }



}