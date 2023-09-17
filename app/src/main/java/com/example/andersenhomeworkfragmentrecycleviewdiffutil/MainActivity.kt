package com.example.andersenhomeworkfragmentrecycleviewdiffutil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.window.layout.WindowMetricsCalculator
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.databinding.ActivityMainBinding
import com.example.andersenhomeworkfragmentrecycleviewdiffutil.utl.DeviceType

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DeviceType.isPhone = isPhone()
        if (DeviceType.isPhone) {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
        }
    }

    //
    private fun isPhone(): Boolean {
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)
        val widthDp = metrics.bounds.width() /
                resources.displayMetrics.density
        return widthDp < 600f
    }

}
