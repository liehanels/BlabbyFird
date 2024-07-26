package com.example.blabbyfird.Threads

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder
import com.example.blabbyfird.Model.BackgroundImage
import com.example.blabbyfird.Model.Birb
import com.example.blabbyfird.Model.Tower
import com.example.blabbyfird.Model.ScreenSize
import com.example.blabbyfird.R
import kotlin.collections.ArrayList
import kotlin.random.Random

class PlayThread : Thread{

    private val TAG : String = "PlayThread"
    private var holder : SurfaceHolder
    private var resources : Resources

    private val FPS : Int = (1000.0/60.0).toInt()
    private val backgroundImage = BackgroundImage()
    private var bitmapImage : Bitmap? = null
    private var startTime : Long = 0
    private var frameTime : Long = 0
    private val velocity = 3
    private val birb : Birb

    //Game state: 0 = Stop; 1 = Running; 2 = Game Over
    private var state : Int = 0
    private var velocityBirb : Int = 0

    var tower : Tower? = null
    val numTower = 2
    val velocityCot = 10
    val minY = 250
    val maxY = ScreenSize.SCREEN_HEIGTH - minY - 500
    val kc = ScreenSize.SCREEN_WIDTH * 3/4
    var towerArray : ArrayList<Tower> = arrayListOf()
    var ran : Random = Random

    var iTower = 0
    var isDead = false


    var isRunning : Boolean = false
        get() = field
        set(value) {
            field = value
        }


    constructor(holder: SurfaceHolder, resources: Resources) {
        this.holder = holder
        this.resources = resources
        isRunning = true
        birb = Birb(resources)

        bitmapImage = BitmapFactory.decodeResource(resources, R.drawable.background)
        bitmapImage = this.bitmapImage?.let { ScaleResize(it) }

        tower = Tower(resources)
        createTower(resources)
    }

    private fun createTower(resources: Resources) {
        for (i in 0 until numTower){
            val tower = Tower(resources)
            tower.x = ScreenSize.SCREEN_WIDTH + kc*i
            tower.y = ran.nextInt(maxY - minY) + minY
            towerArray.add(tower)
        }
    }

    override fun run() {
        Log.d(TAG, "Thread Started")

        while (isRunning){
            if (holder == null) return
            startTime = System.nanoTime()
            val canvas = holder.lockCanvas()
            if(canvas != null){
                try{
                    synchronized(holder){
                        render(canvas)

                        renderBirb(canvas)

                        renderTower(canvas)
                    }
                }
                finally{
                    holder.unlockCanvasAndPost(canvas)
                }
            }
            frameTime = (System.nanoTime() - startTime) / 1000000
            if (frameTime < FPS) {
                try{
                    Thread.sleep( FPS - frameTime)
                }catch (e : InterruptedException){
                    Log.e("Interrupted", "Thread is asleep. Error.")
                }
            }
        }
        Log.d(TAG, "Thread has reached its finale.")
    }

    private fun birbDeath() {
        if (isDead){
            isRunning = false
        }
    }

    private fun renderTower(canvas: Canvas?){
        if(state == 1) {
            if (towerArray.get(iTower).x < birb.x - tower!!.w) {
                iTower++
                if (iTower > numTower - 1) {
                    iTower = 0
                }
            } else if (((towerArray.get(iTower).x) < birb.getBirb().width) &&
                (towerArray.get(iTower).y > birb.y || towerArray.get(iTower).getBottomY() < birb.y + birb.getBirb().height)
            )
                isDead = true


            for (i in 0 until numTower) {// 0, 1, 2
                if (towerArray.get(i).x < - tower!!.w){
                    towerArray.get(i).x = towerArray.get(i).x + numTower * kc
                    towerArray.get(i).y = ran.nextInt(maxY - minY) + minY

                }
                if(!isDead) {
                    towerArray.get(i).x = towerArray.get(i).x - velocityCot
                }
                canvas!!.drawBitmap(
                    tower!!.towerTop,
                    towerArray.get(i).x.toFloat(),
                    towerArray.get(i).getTopY().toFloat(),
                    null
                )
                canvas!!.drawBitmap(
                    tower!!.towerBottom,
                    towerArray.get(i).x.toFloat(),
                    towerArray.get(i).getBottomY().toFloat(),
                    null
                )

            }
        }
    }

    private fun renderBirb(canvas: Canvas?) {
        if(state == 1){
            if(!isDead) {
                if (birb.y < (ScreenSize.SCREEN_HEIGTH - birb.getBirb().height)) {
                    velocityBirb = velocityBirb + 2 // fall down
                    birb.y = birb.y + velocityBirb // fly up
                }
            }
        }
        if(!isDead){
            canvas!!.drawBitmap(birb.getBirb(), birb.x.toFloat(), birb.y.toFloat(), null)
        }
    }
    private fun render(canvas: Canvas?){
        Log.d(TAG, "Render Canvas")
        if(!isDead) {
            backgroundImage.x = backgroundImage.x - velocity
        }
        if(backgroundImage.x < -bitmapImage!!.width) {
            backgroundImage.x = 0
        }
        bitmapImage?.let { canvas!!.drawBitmap(it, (backgroundImage.x).toFloat(),(backgroundImage.y).toFloat(), null)}
        if(backgroundImage.x < -(bitmapImage!!.width - ScreenSize.SCREEN_WIDTH)){
            bitmapImage?.let {canvas!!.drawBitmap(it,(backgroundImage.x + bitmapImage!!.width).toFloat(), (backgroundImage.y).toFloat(), null)}
        }

    }
    private fun ScaleResize(bitmap: Bitmap): Bitmap {
        var ratio : Float = (bitmap.width / bitmap.height).toFloat()
        val scaleWidth : Int = (ratio * ScreenSize.SCREEN_HEIGTH).toInt()
        return Bitmap.createScaledBitmap(bitmap, scaleWidth, ScreenSize.SCREEN_HEIGTH, false)
    }

    fun Jump() {
        state = 1
        if(birb.y > 0) {
            velocityBirb = -30
        }
    }


}