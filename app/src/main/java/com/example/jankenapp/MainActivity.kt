package com.example.jankenapp

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val key:String="INTENT_KEY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Flowable.interval(1500L,TimeUnit.MILLISECONDS)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    select_hand_gu.actionAnim()
                    select_hand_pa.actionAnim()
                    select_hand_pa.actionAnim()
                    doSpringAction(title_janken)
                }.isDisposed
    }


    private fun doSpringAction(item:View)= item.let{ v->
        v.scaleX=1.2f
        v.scaleY=1.2f
        SpringAnimation(v, DynamicAnimation.SCALE_X,0.8f).apply {
            spring.dampingRatio    =   SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            spring.stiffness       =   SpringForce.STIFFNESS_LOW
        }.run { start() }

        SpringAnimation(v,DynamicAnimation.SCALE_Y,0.8f).apply {
            spring.dampingRatio    =   SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            spring.stiffness       =   SpringForce.STIFFNESS_LOW
        }.run { start() }
    }

    private fun ImageView.actionAnim():Unit=
        ObjectAnimator.ofFloat(this,"rotationY",0.0f,1800.0f)
            .setDuration(1500L)
            .start()

    fun onClick(view:View){
        val imgView =view as ImageView
        startActivity(imgView.setIntent(this,key))
    }

}