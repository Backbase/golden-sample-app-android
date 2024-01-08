package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.iterator
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import com.backbase.android.Backbase
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.databinding.ActivityMainBinding
import com.backbase.golden_sample_app.koin.moreMenuModule
import com.backbase.golden_sample_app.router.AppRouting
import kotlinx.coroutines.selects.select
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Single activity approach and the setup of the navigation.
 *
 * Created by Backbase R&D B.V on 17/08/2023
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navigator: AppRouting by inject()
    private lateinit var bottomMenuIds: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            bottomMenuIds = getBottomMenuItemIds()
            setupNavigation()
            loadScopedDependencies()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupNavigation()
    }

    private fun getBottomMenuItemIds(): List<Int> = mutableListOf<Int>().apply {
        binding.bottomNavigation.menu.forEach { menuItem ->
            add(menuItem.itemId)
        }
    }

    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_container)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id in bottomMenuIds) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }
        }
        navigator.bind(navController)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            navController.navigate(item.itemId)
            true
        }
    }

    private fun loadScopedDependencies() {
        loadKoinModules(
            moreMenuModule(Navigation.findNavController(this@MainActivity, R.id.nav_host_container))
        )
    }

}
