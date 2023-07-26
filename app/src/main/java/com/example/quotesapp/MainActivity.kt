package com.example.quotesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation(){
        bottomNavigationView = findViewById(R.id.bttm_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(bottomNavigationView,navController)

    }
}

