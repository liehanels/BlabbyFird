package com.example.blabbyfird

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.blabbyfird.Model.ScreenSize
import com.example.blabbyfird.UI.PlayActivity

class MainActivity : AppCompatActivity() {
    private val Tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ScreenSize.getScreenSize(this)
        buttonStart.SetOnClickListener {
            val iPlayGame = Intent(this@MainActivity, PlayActivity::class.java)
            startActivity(iPlayGame)
            finish()
            Log.d(Tag, "Button Play Activated")
        }
    }
}