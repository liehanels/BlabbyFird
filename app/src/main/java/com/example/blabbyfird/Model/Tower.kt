package com.example.blabbyfird.Model

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.example.blabbyfird.R

class Tower(res : Resources) {
    val towerTop = BitmapFactory.decodeResource(res, R.drawable.cot_top)
        get() = field
    val towerBottom = BitmapFactory.decodeResource(res, R.drawable.cot_bottom)
        get() = field
    val w = towerTop.width
    val h = towerTop.height

    var x : Int = 0
        get() = field
        set(value){
            field = value
        }
    var y : Int = 0
        get() = field
        set(value){
            field = value
        }
    fun getTopY() : Int{
        return y - h
    }
    fun getBottomY() : Int{
        return y + 500
    }
}