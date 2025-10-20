package com.erickvazquezs.practicamodulo7

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.erickvazquezs.practicamodulo7.application.PracticaModulo7Application
import com.erickvazquezs.practicamodulo7.databinding.ActivityMainBinding
import com.erickvazquezs.practicamodulo7.ui.fragments.MoviesListFragment
import com.erickvazquezs.practicamodulo7.utils.Constants
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screenSplash = installSplashScreen()
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        screenSplash.setKeepOnScreenCondition { false }

        supportFragmentManager.beginTransaction().replace(
            R.id.container,
            MoviesListFragment()
        ).commit()
    }
}