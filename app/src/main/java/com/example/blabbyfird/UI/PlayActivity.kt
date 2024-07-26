package com.example.blabbyfird.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PlayActivity : AppCompatActivity() {
    fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val playView = PlayView(this)
        setContentView(playView)
    }
}