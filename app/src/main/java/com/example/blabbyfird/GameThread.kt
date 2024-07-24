package com.example.blabbyfird

import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.View

class GameThread(private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread() {
    private var running: Boolean = false

    fun setRunning(isRunning: Boolean){
        running = isRunning
    }

    override fun run(){
        while(running){
            val canvas: Canvas? = surfaceHolder.lockCanvas()
            if(canvas != null){
                synchronized(surfaceHolder){
                    gameView.update()
                    gameView.draw(canvas)
                }
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }
}