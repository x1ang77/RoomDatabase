package com.joel.todolistfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var i = 0
        val x = i++
        val y = ++i

        Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()

    }
}
// File Manager
// Image Gallery
// ViewPager with RecyclerView