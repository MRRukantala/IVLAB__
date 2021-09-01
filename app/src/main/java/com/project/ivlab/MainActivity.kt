package com.project.ivlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.project.ivlab.berandaactivity.BerandaActivity
import com.project.ivlab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        supportActionBar?.hide()

    }

}