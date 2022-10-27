package com.example.taskapp

import android.opengl.Visibility
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskapp.data.local.Pref
import com.example.taskapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref:Pref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = Pref(this)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (!pref.isShowOnBoarding()){
            navController.navigate(R.id.onBoardingFragment)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.taskFragment,R.id.profileFragment,R.id.onBoardingFragment
            )
        )
        val bottomNavFragments = arrayListOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.profileFragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener {
                controller, destination, arguments ->
            if (bottomNavFragments.contains(destination.id)){
                navView.visibility = VISIBLE
            }else{
                navView.visibility = GONE
            }
            if (destination.id == R.id.onBoardingFragment) {
                supportActionBar?.hide()
            }else supportActionBar?.show()
        }

    }
}