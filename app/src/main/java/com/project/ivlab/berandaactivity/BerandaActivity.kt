package com.project.ivlab.berandaactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.project.ivlab.R
import com.project.ivlab.databinding.ActivityBerandaBinding

class BerandaActivity : AppCompatActivity() {

    lateinit var binding: ActivityBerandaBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_beranda
        )

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.beranda_nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)

        binding.root
    }


}