package com.backbase.accounts_demo.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.backbase.accounts_demo.R
import com.backbase.accounts_demo.databinding.ActivityMainBinding
import com.backbase.app_common.AppRouting
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val navigator: AppRouting by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val navController = findNavController()
        navigator.bind(navController)

        loadKoinModules(
            module {
                factory<NavController> { findNavController() }
            }
        )
    }

    internal fun MainActivity.findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        return navHostFragment.navController
    }
}
