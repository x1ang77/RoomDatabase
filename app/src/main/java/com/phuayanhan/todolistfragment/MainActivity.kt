package com.phuayanhan.todolistfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var i=0
        val x=i++
        val y=++i

        Log.d("MainActivityDebug","Value of x is $x value of y is $y")
    }
}