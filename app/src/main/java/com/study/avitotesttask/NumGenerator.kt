package com.study.avitotesttask

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.os.postDelayed
import java.util.*
import kotlin.properties.Delegates

class NumGenerator(private val visibleElements: TreeSet<Int>, private val deletedElements: TreeSet<Int>,
                   private val elementReceiver : ElementReceiver){

    private val period : Long = 5000
    private val handler = Handler(Looper.getMainLooper())
    var generatedNum by Delegates.notNull<Int>()

    fun start(){
        handler.postDelayed(period){
            generatedNum = if (deletedElements.isEmpty()){
                visibleElements.last()+1
            } else{
                val randomIndex = (0 until deletedElements.size).random()
                deletedElements.elementAt(randomIndex)
            }
            elementReceiver.addElement(generatedNum)
            start()
        }
    }

    interface ElementReceiver{
        fun addElement(num : Int)
    }
}