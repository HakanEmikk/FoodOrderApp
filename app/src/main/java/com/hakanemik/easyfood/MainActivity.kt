package com.hakanemik.easyfood

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.NavigationUI
import com.hakanemik.easyfood.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment1=supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation,navHostFragment1.navController)

        navHostFragment1.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
              R.id.homepageFragment -> binding.bottomNavigation.visibility = View.VISIBLE
                else -> binding.bottomNavigation.visibility = View.GONE
            }

        }
    }
}