package com.example.jankenapp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private val key:String="INTENT_KEY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        intent.getIntExtra(key,-1)
                .setMyHandImg(img_result_myhand)
                .setResult(img_result_com,txt_result)

        img_result_myhand.doTranslationUP()
        img_result_myhand.doRotationX()
        txt_result.doTranslationDown()
        txt_result.doResultTxtAnime()

        fab_back.setOnClickListener { finish() }

        floatingActionButton_omake.setOnClickListener {
            startActivity(Intent(this,MainActivity3::class.java))
        }
    }

    private fun ImageView.doTranslationUP(){
        ObjectAnimator.ofFloat(this, "translationY", 0.0f, -600.0f)
            .setDuration(1000L)
            .start()
    }
    private fun TextView.doTranslationDown(){
        ObjectAnimator.ofFloat(this, "translationY", 0.0f, 400.0f)
            .setDuration(1000L)
            .start()
    }

    private fun ImageView.doRotationX():Unit{
        ObjectAnimator.ofFloat(this,"rotationX",0.0f,3600.0f)
            .setDuration(1000L)
            .start()
    }

    private fun TextView.doResultTxtAnime(){
        ObjectAnimator.ofFloat(this,"alpha",0.0f,500.0f)
            .setDuration(1000L)
            .start()
    }
}