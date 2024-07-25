package com.example.blabbyfird

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private val thread: GameThread
    private val birb: Birb
    private val towers = mutableListOf<Tower>()
    private val paint = Paint()
    private val background: Bitmap
    private var score = 0
    private var startTime: Long = 0

    init {
        holder.addCallback(this)
        birb = Birb(context)
        towers.add(Tower(context, 800f, 400))
        towers.add(Tower(context, 1600f, 300))
        thread = GameThread(holder, this)
        background = BitmapFactory.decodeResource(resources, R.drawable.background)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        birb.draw(canvas = Canvas(), Paint())
        towers[0].draw(canvas = Canvas())
        towers[1].draw(canvas = Canvas())
        thread.setRunning(true)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        TODO("Not yet implemented")
    }

    fun update() {
        TODO("Not yet implemented")
    }
}