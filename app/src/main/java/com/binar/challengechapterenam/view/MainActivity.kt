 package com.binar.challengechapterenam.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.binar.challengechapterenam.R

 class MainActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
     }

     override fun onBackPressed() {
         if (!findNavController(R.id.fragmentContainerView).popBackStack()) {
             super.onBackPressed()
         }

     }
 }