package com.example.smartgarden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartgarden.databinding.ActivityMainBinding
import com.example.smartgarden.ui.profile.ProfileFragment
import com.example.smartgarden.ui.upload.UploadActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigationView()

        // Find the CardView with ID card_camera
        val cardCamera: CardView = findViewById(R.id.card_camera)
        val btnScan = findViewById<View>(R.id.btn_scan)
        // Set up an OnClickListener for the CardView
        cardCamera.setOnClickListener {
            // Start the UploadActivity when the CardView is clicked
            startActivity(Intent(this@MainActivity, UploadActivity::class.java))
        }
        btnScan.setOnClickListener{
            // Start the UploadActivity when btnScan is clicked
            startActivity(Intent(this@MainActivity, UploadActivity::class.java))
        }
    }

    private fun setBottomNavigationView(){
        setSupportActionBar(binding.bottomAppBar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.communityFragment,
            R.id.none,
            R.id.myplantFragment,
            R.id.profileFragment
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}