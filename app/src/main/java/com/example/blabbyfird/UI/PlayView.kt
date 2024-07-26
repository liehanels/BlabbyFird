package com.example.blabbyfird.UI

import android.content.Context
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.blabbyfird.Threads.PlayThread

class PlayView(context: Context?) : SurfaceView(context), SurfaceHolder.Callback {
    private val TAG = "PlayView"
    private  var playThread : PlayThread? = null

    init {
        val holder = holder
        holder.addCallback(this)
        isFocusable = true
        playThread = PlayThread(holder, resources)
    }
    override fun surfaceCreated(holder: SurfaceHolder) {
        if(!playThread!!.isRunning){
            playThread = holder.let {
                PlayThread(it!!, resources)
            }
        }else{
            playThread!!.start()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        if(playThread!!.isRunning){
            playThread!!.isRunning = false
            var isCheck : Boolean = true
            while (isCheck) {
                try{
                    playThread!!.join()
                }catch (e : InterruptedException) {

                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val ev = event!!.action
        if(ev == MotionEvent.ACTION_DOWN){
            playThread?.Jump()
        }
        return true
    }

}