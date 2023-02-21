package com.joel.todolistfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val greeting = GreetingImpl()
        greeting.greeting()

        val list = mutableListOf<Int>(1, 2, 3, 4)
        val res = list.add2(1, 2)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onNavigateUp()
    }
}

fun MutableList<Int>.add2(a: Int, b: Int): Int {
    return this[a] + this[b]
}

interface Greetings {
    fun greeting()
}

class BaseGreeting : Greetings {
    override fun greeting() {
        Log.d("debugging", "Hello from kotlin delegation, Base Greeting, greeting()")
    }
}

class GreetingImpl : Greetings by BaseGreeting()
// File Manager
// Image Gallery
// ViewPager with RecyclerView