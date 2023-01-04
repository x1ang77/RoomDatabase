package com.justin.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // i++: assignment occurs first before increment
        // ++i: increment occurs first before assignment
        var i = 0
        val x = i++ // x is 0 because i is first assigned before i becomes 1 from 0
        val y = ++i // y is 2 because i becomes 2 from 1 before being assigned to y
        Log.d("Calculation", "$x and $y")

//        Toast.makeText(this.applicationContext, "Hello", Toast.LENGTH_LONG).show()
    }
}

// fragments are lightweight compared to using activities
// safeargs are used to pass arguments. the arguments are not nullable, and don't need to type
// getString or getShort, etc