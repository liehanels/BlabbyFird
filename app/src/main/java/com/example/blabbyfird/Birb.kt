package com.example.blabbyfird

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

class Birb(context: Context) {
    private var birbBitMap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.blabbyfird_birb)
    var x: Float = 100f
    var y: Float = 100f
    var velocity: Float = 0f

    fun draw(canvas: Canvas, paint: Paint){
        canvas.drawBitmap(birbBitMap, x, y, paint)
    }
    fun update(){
        y += velocity
        velocity += 1f
    }
    fun flap(){
        velocity = -20f
    }
    fun getRectangle(): Rect {
        return Rect(x.toInt(), y.toInt(), (x + birbBitMap.width).toInt(), (y + birbBitMap.height).toInt())
    }
}