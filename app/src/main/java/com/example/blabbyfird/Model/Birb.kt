package com.example.blabbyfird.Model

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.blabbyfird.R

class Birb (res: Resources) {
    var x : Int = 0
        get() = field
        set(value) {
            field = value
        }
    var y : Int = 0
        get() = field
        set(value) {
            field = value
        }
    val maxFrame : Int = 7
    var currentFrame : Int = 0
        get() = field
        set(value){
            field = value
        }
    var birbBitMap: Bitmap = BitmapFactory.decodeResource(res, R.drawable.blabbyfird_birb)
    init {
        x = ScreenSize.SCREEN_WIDTH/2 - birbBitMap.width/2
        y = ScreenSize.SCREEN_WIDTH/2 - birbBitMap.width/2
    }
    fun getBirb() : Bitmap{
        return birbBitMap
    }
}