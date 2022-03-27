package com.example.jankenapp

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

fun ImageView.setIntent(context: Context,key:String):Intent{
    val intent =  Intent(context,MainActivity2::class.java)
    when(this.id){
        R.id.select_hand_gu     ->  intent.apply { putExtra(key,0) }
        R.id.select_hand_choki  ->  intent.apply { putExtra(key,1) }
        R.id.select_hand_pa     ->  intent.apply { putExtra(key,2) }
    }
        return  intent
}

fun Int.setMyHandImg(mhv:ImageView):Int= when (this){
    0   ->{
            mhv.setImageResource(R.drawable.janken_gu)
            0
        }
    1   ->{
            mhv.setImageResource(R.drawable.janken_choki)
            1
        }
    2  ->{
            mhv.setImageResource(R.drawable.janken_pa)
            2
        }
    else -> 0
}

fun Int.setResult(com:ImageView,txtR:TextView){
    val comP=Random.nextInt(3)
    when(comP){
        0   ->  com.setImageResource(R.drawable.cpu_janken_gu)
        1   ->  com.setImageResource(R.drawable.cpu_janken_choki)
        2   ->  com.setImageResource(R.drawable.cpu_janken_pa)
    }
    when ((this-comP+3)%3){
        0   ->  txtR.text   =   "引き分けです。"
        1   ->  txtR.text   =   "負けました。"
        2   ->  txtR.text   =   "勝ちました。"
    }
}


