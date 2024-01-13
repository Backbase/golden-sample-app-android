package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.Navigation
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.databinding.ActivityMainBinding
import com.backbase.golden_sample_app.menu.moreMenuModule
import com.backbase.golden_sample_app.router.AppRouting
import com.backbase.golden_sample_app.session.SessionManager
import com.backbase.golden_sample_app.session.sessionModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

/**
 * Single activity approach and the setup of the navigation.
 *
 * Created by Backbase R&D B.V on 17/08/2023
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navigator: AppRouting by inject()
    private lateinit var bottomNavigationHandler: BottomNavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        bottomNavigationHandler = BottomNavigationHandler(
            binding.bottomNavigation,
            Navigation.findNavController(this, R.id.nav_host_container),
            navigator
        )

        if (savedInstanceState == null) {
            bottomNavigationHandler.setupNavigation()
            loadScopedDependencies()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bottomNavigationHandler.setupNavigation()
    }

    private fun loadScopedDependencies() {
        val navController = Navigation.findNavController(this@MainActivity, R.id.nav_host_container)
        loadKoinModules(
            listOf(
                sessionModule(navController),
                moreMenuModule(navController)
            )
        )
    }

}
