package com.erickvazquezs.practicamodulo7

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.erickvazquezs.practicamodulo7.application.PracticaModulo7Application
import com.erickvazquezs.practicamodulo7.databinding.ActivityMainBinding
import com.erickvazquezs.practicamodulo7.utils.Constants
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val repository = (application as PracticaModulo7Application).repository

        lifecycleScope.launch {
            try {
                val movies = repository.getMovies()

                Log.d(Constants.LOGTAG, movies.toString())
            } catch(e: Exception) {
                Log.d(Constants.LOGTAG, "Error: ${e.printStackTrace()}")
            }
        }
    }
}