package br.com.jaquesprojetos.eletriccarapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import br.com.jaquesprojetos.eletriccarapp.R
import br.com.jaquesprojetos.eletriccarapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListener()
        val navController = findNavController(
            R.id.nav_host_fragment
        )
        setupWithNavController(binding.bottomNavigation, navController)


    }

    private fun setupListener() {
        binding.fabCalculate.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CalculateAutonomyActivity::class.java
                )
            )
        }
    }
}
